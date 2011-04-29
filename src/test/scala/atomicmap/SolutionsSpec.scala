package atomicmap

import collection.mutable.ConcurrentMap

//// uncomment to see how j.u.c.ConcurrentHashMap fails
//class JUCCHMAtomicMapSpec extends AtomicMapSpec {
//  def createMap[A, B]: ConcurrentMap[A, B] = new ConcurrentHashMap[A, B]
//}

class NonConcurrentAtomicMapSpec extends AtomicMapSpec {
  def createMap[A, B]: ConcurrentMap[A, B] = new NonConcurrentConcurrentMap[A, B]
}

class LazyConcurrentMapSpec extends AtomicMapSpec {
  def createMap[A, B]: ConcurrentMap[A, B] = new LazyConcurrentMap[A, B]
}

class HackedConcurrentHashMapWrapperSpec extends AtomicMapSpec {
  def createMap[A, B]: ConcurrentMap[A, B] = new HackedConcurrentHashMapWrapper[A, B](new HackedJUCConcurrentHashMap[A, B])
}
