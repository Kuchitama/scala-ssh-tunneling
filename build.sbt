import sbt.Keys._
import sbt._

name := "scala-ssh-tunneling"
scalaVersion := "2.12.1"
organization:= "com.github.kuchitama"
version := "0.1.0"

libraryDependencies ++= Seq(
  "com.jcraft"            %   "jsch"                    % "0.1.54"
)

crossScalaVersions := Seq("2.11.8")

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Xfatal-warnings")