[![Release](https://jitpack.io/v/Kuchitama/scala-ssh-tunneling.svg)](https://jitpack.io/#Kuchitama/scala-ssh-tunneling)

# Usages

## build.sbt
```sbt
resolvers += "jitpack" at "https://jitpack.io"

...

libraryDependencies += "com.github.Kuchitama" % "scala-ssh-tunneling" % "0.1.0"	
```

## Your Code
```scala
import java.net.URL
import com.github.kuchitama.ssh_tunneling.SshTunnel

val result = SshTunnel("STAGE_HOST", "USER", Option("PASSWORD_OR_KEY_PHRASE"), Option[URL](new URL("IDENTITY_FILE_PATH"))).tunnel("REMOTE_HOST", REMOTE_PORT) {
  // any processes ... 
}
```

# License

Copyright (c) 2017 Kuchitama

Released under the MIT license
[http://opensource.org/licenses/mit-license.php](http://opensource.org/licenses/mit-license.php)