ThisBuild / scalaVersion := "2.13.1"
ThisBuild / version      := "0.1.0"

lazy val app = (project in file("."))
  .settings(
    name := "TicTacToe"
  )

lazy val tte_core = (project in file("core"))
  .settings(
    name := "TTE_Core"
  )

lazy val tte_interactions = (project in file("interactions"))
  .settings(
    name := "TTE_Interactions"
  )

lazy val simple_promt = (project in file("simple_promt"))
  .dependsOn(tte_core)
  .settings(
    name := "TTE_SimplePromt"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % "test"
