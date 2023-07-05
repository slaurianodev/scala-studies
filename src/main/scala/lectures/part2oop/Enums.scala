package lectures.part2oop

object Enums {

  enum Permissions {
    case READ, WRITE, EXECUTE, NONE

    def openDocument(): Unit =
      if(this == READ) println("opening document...")
      else println("reading not allowed.")
  }

  val somePermissions: Permissions = Permissions.READ

  // constructor args
  enum PermissionWithBits(bits: Int) {
    case READ extends PermissionWithBits(4) // 100
    case WRITE extends PermissionWithBits(2) // 010
    case EXECUTE extends PermissionWithBits(1) // 010
    case NONE extends PermissionWithBits(0) // 000
  }

  object PermissionWithBits {
    def fromBits(bits: Int): PermissionWithBits =
      PermissionWithBits.NONE
  }

  // standard API
  val somePermissionsOrdinal = somePermissions.ordinal
  val allPermissions = PermissionWithBits.values // array of all possible values of the enum
  val readPermission: Permissions = Permissions.valueOf("READ") // Permissions.READ

  def main(args: Array[String]): Unit = {
    somePermissions.openDocument()
    println(somePermissionsOrdinal)
  }
}
