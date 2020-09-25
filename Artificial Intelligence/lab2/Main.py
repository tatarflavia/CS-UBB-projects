from Console.Console import Console
from Domain.Problem import Problem
from Domain.State import State
from Service.Controller import Controller


problem = Problem()
controller = Controller(problem)
console=Console(controller)
console.runApp()