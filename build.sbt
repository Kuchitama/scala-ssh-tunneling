import sbt.Keys._
import sbt._

name := "scala-ssh-tunneling"
scalaVersion := "2.11.8"
organization:= "com.github.kuchitama"
version := "0.1.0_2.11"

libraryDependencies ++= Seq(
  "com.jcraft"            %   "jsch"                    % "0.1.54"
)

crossScalaVersions := Seq("2.11.8")

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Xfatal-warnings")