from Repository.Repository import Repository
from Service.Controller import Controller
from Console.Console import Console

repo=Repository()
ctrl=Controller(repo)
console=Console(ctrl)
console.gradientDescent()