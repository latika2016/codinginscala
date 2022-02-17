import java.util.concurrent.atomic.AtomicInteger


case class Dragon(hitPoints: Int, isAlive: Boolean)

object DragonKata extends App {
  val damageCount:AtomicInteger = new AtomicInteger()
  var hitPoints = 20

  def doDamage(damage: Int): Dragon = {
    val newHitPoints = hitPoints - damage
    if (newHitPoints <= damage) {
      println("Dragon was hit for "+damage+" and is now dead.")
      Dragon(0, false)
    }
    else {
      damageCount.incrementAndGet()
      println("Dragon was damage by "+damage+" hitpoints. Current hitpoints: "+newHitPoints)
      Dragon(newHitPoints, true)
    }
  }

  println(doDamage(5))
}