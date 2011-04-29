package atomicmap

import collection.JavaConversions.JConcurrentMapWrapper

/**
 * Override the default JConcurrentMapWrapper's getOrElseUpdate() with an atomic version.
 *
 * See HackedJUCConcurrentHashMap.java (also in this project) for info on putIfAbsentLazy().
 */
class HackedConcurrentHashMapWrapper[A, B](underlying: HackedJUCConcurrentHashMap[A, B]) extends JConcurrentMapWrapper[A, B](underlying) {
  override def getOrElseUpdate(key: A, op: => B): B = {
    val newval = new Lazy[B] { lazy val get: B = op }
    underlying.putIfAbsentLazy(key, newval) match {
      case oldval if oldval != null =>
        // value was already there, don't eval newval, just return old one
        oldval
      case null =>
        // No previous value found for key.
        // Our value just went in and was evaluated inside HackedJUCConcurrentHashMap.
        // Calling get() again just returns the value that is now in the map.
        newval.get
    }
  }
}
