package scales

package object utils extends collection.IterableUtils 
  with AsBooleanTrait
  with collection.StackUtils
  with iteratee.Iteratees
  with EquivFunctions
  with collection.Trees
  with collection.path.Paths
  with collection.ConcurrentMapUtils
{
  def error(str : String) = Predef.error(str)

// https://issues.scala-lang.org/browse/SI-4767 forces them to be here if we want them inlining

  import scala.collection.IndexedSeqLike
  import scala.collection.generic.CanBuildFrom
  
  import collection.Tree

  @inline final def item[Item <: LeftLike[Item, Tree[Item, Section, CC]], Section, CC[X] <: IndexedSeqLike[X, CC[X]]](item: Item): ItemOrTree[Item, Section, CC] = item

  @inline final def subtree[Item <: LeftLike[Item, Tree[Item, Section, CC]], Section, CC[A] <: IndexedSeqLike[A, CC[A]]](section: Section, children: CC[ItemOrTree[Item, Section, CC]])(implicit cbf : TreeCBF[Item, Section, CC]): ItemOrTree[Item, Section, CC] = Tree[Item, Section, CC](section, children)

  @inline final def one[T]( i : T ) : List[T] =
      i :: Nil

  /**
   * Simple grabber of resources
   */ 
  def resource(a : AnyRef, path : String) =
    a.getClass.getResource(path)

  import java.nio.charset.Charset
  val UTF_8 = Charset.forName("UTF-8")
  val US_ASCII = Charset.forName("US-ASCII")

  /**
   * A usable default of UTF8 NOT the vm's Charset.defaultCharset based on its locale, use vmDefaultCharset for that
   */
  val defaultCharset = UTF_8

}
