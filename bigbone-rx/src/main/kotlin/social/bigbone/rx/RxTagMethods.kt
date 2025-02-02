package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.entity.Tag
import social.bigbone.api.method.TagMethods

/**
 * Reactive implementation of [TagMethods].
 * View information about or follow/unfollow hashtags.
 * @see <a href="https://docs.joinmastodon.org/methods/tags/">Mastodon tags API methods</a>
 */
class RxTagMethods(client: MastodonClient) {
    private val tagMethods = TagMethods(client)

    /**
     * Show a hashtag and its associated information.
     * @param tagId The name of the hashtag
     * @return information about a single tag
     */
    fun getTag(tagId: String): Single<Tag> {
        return Single.create {
            try {
                val results = tagMethods.getTag(tagId)
                it.onSuccess(results.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    /**
     * Follow a hashtag. Posts containing a followed hashtag will be inserted into your home timeline.
     * @param tagId The name of the hashtag
     * @return information about a single tag
     */
    fun followTag(tagId: String): Single<Tag> {
        return Single.create {
            try {
                val results = tagMethods.followTag(tagId)
                it.onSuccess(results.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }

    /**
     * Unfollow a hashtag. Posts containing this hashtag will no longer be inserted into your home timeline.
     * @param tagId The name of the hashtag
     * @return information about a single tag
     */
    fun unfollowTag(tagId: String): Single<Tag> {
        return Single.create {
            try {
                val results = tagMethods.unfollowTag(tagId)
                it.onSuccess(results.execute())
            } catch (e: Throwable) {
                it.onError(e)
            }
        }
    }
}
