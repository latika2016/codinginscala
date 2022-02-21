import java.util.concurrent.atomic.AtomicInteger
import java.util.logging.{Level, Logger}

case class Dragon(hitPoints: Int, isAlive: Boolean)
case class DragonAndResult(dragon: Dragon, message: String)

object Dragon {
  val damageCount:AtomicInteger = new AtomicInteger()
  val logger = Logger.getLogger("Dragon")

  def handleErrors:(Attack, Exception) => DragonAndResult = {
    case (attack, e: Exception) => {
      logger.log(Level.SEVERE, "Unexpected error damaging " + this + " for " + attack.damage + " hitpoints", e)
      throw e
    }
  }
}


case class Attack(dragon: Dragon, damage: Int)

object Attack extends App {
  def doDamage(dragon: Dragon, damage: Int): Dragon = {
    val newHitPoints = dragon.hitPoints - damage
    if (newHitPoints <= damage) {
      println("Dragon was hit for "+damage+" and is now dead.")
      Dragon(0, false)
    }
    else {
      damageCount.incrementAndGet()
      println("Dragon was damaged by "+damage+" hitpoints. Current hitpoints: "+newHitPoints)
      Dragon(newHitPoints, true)
    }
  }

  println(doDamage(5))
}