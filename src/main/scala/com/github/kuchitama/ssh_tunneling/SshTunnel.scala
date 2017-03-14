package com.github.kuchitama.ssh_tunneling

import java.net.URL
import com.jcraft.jsch.{JSch, Session}

case class SshTunnel(host: String, user: String, password: Option[String] = None, privateKey: Option[URL] = None, port: Int = 22) {
  require(password.nonEmpty || privateKey.nonEmpty)

  def tunnel[A](rhost:String, rport: Int)(block: => A): A = {
    tunnel[A](rhost, rport, rport)(block)
  }

  def tunnel[A](rhost: String, rport: Int, lport: Int)(block: =>A): A = {
    try {
      val result = createSession().map { session =>
        println("Establishing Connection...")
        session.connect()
        val assingedPort = session.setPortForwardingL(lport, rhost, rport)

        println("localhost:" + assingedPort + " -> " + rhost + ":" + rport)

        val res: A = block

        res
      }
      result.get
    } catch {
      case e: Exception =>
        println(e)
        throw e
    } finally {
      SshTunnel.session.map(_.disconnect())
      SshTunnel.session = None
    }
  }

  private def createSession() = {
    if (SshTunnel.session.isEmpty) {
      val jSch = new JSch()

      if ( privateKey.nonEmpty ) {
        val keyPath = privateKey.get.getPath
        println(s"load identity: ${keyPath}")
        if ( password.isEmpty ) {
          jSch.addIdentity(keyPath)
        } else {
          jSch.addIdentity(keyPath, password.get)
        }
      }

      val session: Session = jSch.getSession(user, host, port)
      SshTunnel.session = Some(session)

      if (privateKey.isEmpty) {
        println(s"connect with password.")
        session.setPassword(password.get)
      }

      session.setConfig("StrictHostKeyChecking", "no")
    }
    SshTunnel.session
  }
}

object SshTunnel {
  private var session:Option[Session] = None
}
