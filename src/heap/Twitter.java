package heap;

import java.util.*;

/**
 * Represents a single tweet with a global timestamp and an ID.
 */
class Tweet {

    /** Global timestamp assigned at post time — higher means more recent. */
    int time;

    /** The tweet's ID as posted by the user. */
    int id;

    /**
     * Constructs a Tweet.
     *
     * @param time global timestamp of the tweet
     * @param id   ID of the tweet
     */
    public Tweet(int time, int id) {
        this.time = time;
        this.id   = id;
    }
}

/**
 * Represents a tweet entry in the K-way merge heap.
 * Carries enough context to advance the frontier to the previous tweet
 * from the same user after this node is polled.
 */
class TweetNode {

    /** The tweet at this position in the user's list. */
    Tweet tweet;

    /** The user who posted this tweet — used to look up their tweet list. */
    int userId;

    /** Index into the user's tweet list — decremented to walk backwards. */
    int index;

    /**
     * Constructs a TweetNode for heap insertion.
     *
     * @param tweet  the Tweet object at this position
     * @param userId ID of the user who posted it
     * @param index  index of this tweet in the user's tweet list
     */
    public TweetNode(Tweet tweet, int userId, int index) {
        this.tweet  = tweet;
        this.userId = userId;
        this.index  = index;
    }
}

/**
 * LeetCode: 355 - Design Twitter
 *
 * <p>Design a simplified version of Twitter with the following operations:
 * <ul>
 *   <li>{@link #postTweet(int, int)}  — user posts a tweet.</li>
 *   <li>{@link #getNewsFeed(int)}     — retrieve the 10 most recent tweet IDs
 *       from the user and all followees.</li>
 *   <li>{@link #follow(int, int)}     — user starts following another user.</li>
 *   <li>{@link #unfollow(int, int)}   — user stops following another user.</li>
 * </ul>
 *
 * <p><b>Approach:</b> HashMap + Max-Heap (K-way merge).
 * <ul>
 *   <li>Each user owns a {@link List} of {@link Tweet} objects in insertion
 *       order (oldest first — tail is most recent).</li>
 *   <li>Follow relationships stored in a {@code Map<userId, Set<followeeId>>}.
 *       A user implicitly follows themselves so their own tweets appear
 *       in their feed.</li>
 *   <li>{@code getNewsFeed} seeds a max-heap with the latest tweet from
 *       every followee then merges like LC 23 (Merge K Sorted Lists),
 *       collecting up to 10 results.</li>
 * </ul>
 *
 * <p><b>Trace (basic example):</b>
 * <pre>
 * postTweet(1, 5)  → tweets={1:[Tweet(t0,5)]}
 * getNewsFeed(1)   → seed heap: TweetNode(Tweet(t0,5), user1, idx0) → feed=[5]
 * follow(1, 2)     → followings={1:{1,2}}
 * postTweet(2, 6)  → tweets={2:[Tweet(t1,6)]}
 * getNewsFeed(1)   → seed: TweetNode(t0,user1,0), TweetNode(t1,user2,0)
 *                  → poll t1 → feed=[6], poll t0 → feed=[6,5]
 * unfollow(1, 2)   → followings={1:{1}}
 * getNewsFeed(1)   → seed: TweetNode(t0,user1,0) → feed=[5]
 * </pre>
 *
 * <p><b>Complexity:</b>
 * <ul>
 *   <li>{@code postTweet}  : O(1)</li>
 *   <li>{@code follow}     : O(1)</li>
 *   <li>{@code unfollow}   : O(1)</li>
 *   <li>{@code getNewsFeed}: O(F log F + 10 log F) where F = followee count</li>
 * </ul>
 */
class Twitter {

    /** Global clock — incremented on every tweet so newer tweets have higher timestamps. */
    int timeStamp = 0;

    /**
     * userId → list of Tweet objects in posting order (oldest first).
     * Polling from the tail gives the most recent tweet efficiently.
     */
    final Map<Integer, List<Tweet>> tweets = new HashMap<>();

    /**
     * userId → set of followeeIds.
     * A user is always added to their own follow set so their tweets
     * automatically appear in their own feed.
     */
    final Map<Integer, Set<Integer>> followings = new HashMap<>();

    public Twitter() {
    }

    /**
     * Composes a new tweet for the given user.
     *
     * @param userId  the author's user ID
     * @param tweetId the tweet's ID
     */
    public void postTweet(int userId, int tweetId) {
        tweets.computeIfAbsent(userId, k -> new ArrayList<>())
                .add(new Tweet(timeStamp++, tweetId));
        ensureFollowsSelf(userId);
    }

    /**
     * Returns up to 10 most recent tweet IDs in the news feed for {@code userId}.
     * Includes tweets from the user and all users they follow, newest first.
     *
     * @param userId the requesting user's ID
     * @return list of up to 10 tweet IDs, most recent first
     */
    public List<Integer> getNewsFeed(int userId) {
        ensureFollowsSelf(userId);
        PriorityQueue<TweetNode> maxHeap = new PriorityQueue<>(
                (a, b) -> Integer.compare(b.tweet.time, a.tweet.time)
        );
        Set<Integer> followees = followings.get(userId);
        for (int followeeId : followees) {
            List<Tweet> userTweets = tweets.get(followeeId);
            if (userTweets != null && !userTweets.isEmpty()) {
                int idx = userTweets.size() - 1;
                maxHeap.offer(new TweetNode(userTweets.get(idx), followeeId, idx));
            }
        }
        List<Integer> feed = new ArrayList<>();
        while (!maxHeap.isEmpty() && feed.size() < 10) {
            TweetNode curr = maxHeap.poll();
            feed.add(curr.tweet.id);
            int nextIdx = curr.index - 1;
            if (nextIdx >= 0) {
                List<Tweet> userTweets = tweets.get(curr.userId);
                maxHeap.offer(new TweetNode(userTweets.get(nextIdx), curr.userId, nextIdx));
            }
        }
        return feed;
    }

    /**
     * Makes {@code followerId} follow {@code followeeId}.
     * No-op if already following.
     *
     * @param followerId the user who wants to follow
     * @param followeeId the user to be followed
     */
    public void follow(int followerId, int followeeId) {
        ensureFollowsSelf(followerId);
        followings.get(followerId).add(followeeId);
    }

    /**
     * Makes {@code followerId} unfollow {@code followeeId}.
     * A user cannot unfollow themselves — silently ignored.
     *
     * @param followerId the user who wants to unfollow
     * @param followeeId the user to be unfollowed
     */
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        ensureFollowsSelf(followerId);
        followings.get(followerId).remove(followeeId);
    }

    /**
     * Ensures a user's follow set exists and contains themselves.
     * Called lazily on every public method to avoid null checks elsewhere.
     *
     * @param userId user to initialise
     */
    public void ensureFollowsSelf(int userId) {
        followings.putIfAbsent(userId, new HashSet<>());
        followings.get(userId).add(userId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */