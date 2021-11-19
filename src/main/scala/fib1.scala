import akka.actor._


object fib1 extends App {

  case class N(num: Int)

  class Guardian(fibNum: ActorRef) extends Actor {

    def receive = {
      case N(num) => println(num)
      //case N(_)<0 => println("Error, integer should be bigger then 0")
      case _ => println("Please enter a integer")
    }
  }

  /*def fibonnaci( n : Int) : Int = n match {
    case 0 => n
    case 1 => n
    case 2 => 1
    case _ => fibonnaci(n-1) + fibonnaci(n-2)
  }*/

  class FibNum extends Actor{
    def receive={
      case N(num)=> if(num==1 || num==2){
        sender ! 1
      }
      else if(num==0){
        sender ! 0
      }
      else if(num<0){
        println("Error, integer must be bigger or equal to 0")
      }
      else {
        sender ! ((num-1) + (num-2))
      }
    }

  }
      val num1 = N(0)
      val num2 = N(1)
      val num3 = N(2)
      val num4 = N(3)
      val num5 = N(4)
      val num6 = N(5)

      val actorSystem = ActorSystem("ActorSystem"); // Creating ActorSystem
      val actor = actorSystem.actorOf(Props[Guardian], "actor") //Creating actor

      actor ! "Hello World"
      actor ! num1
      actor ! num2
      actor ! num3
      actor ! num4
      actor ! num5
      actor ! num6
  actorSystem.terminate()
}