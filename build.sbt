ThisBuild / scalaVersion := "2.13.1"
ThisBuild / version      := "0.1.0"

lazy val app = (project in file("."))
  .settings(
    name := "TicTacToe"
  )

lazy val tte_core = (project in file("simple_promt"))
  .settings(
    name := "TTE_Core"
  )

lazy val simple_promt = (project in file("simple_promt"))
  .dependsOn(tte_core)
  .settings(
    name := "TTE_SimplePromt"
  )