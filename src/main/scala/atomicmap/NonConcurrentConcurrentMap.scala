package atomicmap

import collection.mutable.{SynchronizedMap, ConcurrentMap}

/**
 * You can pass the supplied tests with a non-concurrent solution.
 * This is not a real entry to the challenge!
 * See other "real" implementations in this package.
 */
class NonConcurrentConcurrentMap[A, B] extends collection.mutable.HashMap[A, B] with SynchronizedMap[A, B] with ConcurrentMap[A, B] {

  def replace(k: A, v: B): Option[B] =
    synchronized {
      get(k) match {
        case Some(v) => put(k, v)
        case _ => None
      }
    }    

  def replace(k: A, oldvalue: B, newvalue: B): Boolean =
    synchronized {
      get(k) match {
        case Some(v) if v == oldvalue =>
          put(k, newvalue)
          true
        case _ =>
          false
      }
    }

  def remove(k: A, v: B): Boolean =
    synchronized {
      get(k) match {
        case Some(curVal) if curVal == v =>
          remove(k).isDefined
        case _ =>
          false
      }
    }

  def putIfAbsent(k: A, v: B): Option[B] = put(k, v)

  // herp derp
  override def getOrElseUpdate(key: A, op: => B): B =
    synchronized {
      super.getOrElseUpdate(key, op)
    }

}