from repository import *
from helper import *
from validate import *

def ui_insert(l, x):
    x = x.split(" ")
    a, b = x[1].split("+")
    b = b.split("i")
    b = b[0]
    a = int(a)
    b = int(b)
    x[3] = int(x[3])
    if x[3] < len(l):
        c = CreateComplex(a, b)
        l = insertElToPos(l, c, x[3])
        PrintList(l, 0, len(l) - 1)
    else:
        print("Invalid numbers!Position must be smaller than " + str(len(l)) + "!")
        raise ValueError("Invalid numbers!Position must be smaller than " + str(len(l)) + "!")


def ui_add(l, x):
    x = x.split(" ")
    a, b = x[1].split("+")
    b = b.split("i")
    b = b[0]
    c = CreateComplex(int(a), int(b))
    l = addElToList(l, c)



def ui_replace(l, x):
    x = x.split(" ")
    if len(x) != 4:
        print("Invalid input!Try again!")
        raise ValueError("Invalid input!Try again!")
    else:

        a1, b1 = x[1].split("+")
        b1 = b1.split("i")
        b1 = b1[0]
        a1 = int(a1)
        b1 = int(b1)
        c1 = CreateComplex(a1, b1)
        a2, b2 = x[3].split("+")
        b2 = b2.split("i")
        b2 = b2[0]
        a2 = int(a2)
        b2 = int(b2)
        c2 = CreateComplex(a2, b2)
        if ExistEl(l, c1) == True:
            l = replaceEl(l, c1, c2)
            PrintList(l, 0, len(l) - 1)
        else:
            print("No " + str(a1) + "+" + str(b1) + "*i in the list!Choose another complex number!")
            raise ValueError("No " + str(a1) + "+" + str(b1) + "*i in the list!Choose another complex number!")


def ui_remove(l, x):
    x = x.split(" ")
    if len(x) == 2:
        x[1] = int(x[1])
        if x[1] < len(l):
            l = RemovePos(l, x[1])
            PrintList(l, 0, len(l) - 1)

        else:
            print("Invalid position!Position must be between 0 and " + str(len(l) - 1) + "!")
            raise ValueError("Invalid position!Position must be between 0 and " + str(len(l) - 1) + "!")
    elif len(x) == 4:
        x[1] = int(x[1])
        x[3] = int(x[3])
        if x[1] <= x[3] and x[3] < len(l) and x[1] >= 0 and x[3] >= 0:
            l = RemovePositions(l, x[1], x[3])
            PrintList(l, 0, len(l) - 1)


        else:
            print("Invalid positions!Positions must be between 0 and " + str(
                len(l) - 1) + "!The start position must be smaller than the end position!")


def UIModulosmall(l, x):
    sf = []
    sf = ModuloSm(l, x)
    if len(sf) == 0:
        print("No such numbers in the list!")
    else:
        PrintList(sf, 0, len(sf) - 1)


def UIModuloequal(l, x):
    sf = []
    sf = ModuloEq(l, x)
    if len(sf) == 0:
        print("No such numbers in the list!")
    else:
        PrintList(sf, 0, len(sf) - 1)


def UIModulogreater(l, x):
    sf = []
    sf = ModuloGr(l, x)
    if len(sf) == 0:
        print("No such numbers in the list!")
    else:
        PrintList(sf, 0, len(sf) - 1)


def ui_list(l, x):
    if x == 'list':
        PrintList(l, 0, len(l) - 1)
    else:
        x = x.split(" ")
        if x[1] == 'real':
            x[2] = int(x[2])
            x[4] = int(x[4])
            if x[2] <= x[4] and x[4] < len(l):
                PrintListReals(l, x[2], x[4])
            else:
                print(
                    "Invalid positions!Please let the start position be smaller than the finish position and the finish be smaller than " + str(
                        len(l)) + "!")
                raise ValueError(
                    "Invalid positions!Please let the start position be smaller than the finish position and the finish be smaller than " + str(
                        len(l)) + "!")
        elif x[1] == 'modulo':
            if x[2] == '>':
                return UIModulogreater(l, x[2])
            elif x[2] == '=':
                return UIModuloequal(l, x[2])
            elif x[2] == '<':
                return UIModulosmall(l, x[2])


def ui_sum(l, x):
    x = x.split(" ")
    x[1] = int(x[1])
    x[3] = int(x[3])
    if x[1] >= 0 and x[3] >= 0 and x[1] <= x[3] and x[3] < len(l) and x[1] < len(l):
        s = DoSum(l, x[1], x[3])
        print("sum " + str(x[1]) + " to " + str(x[3]) + ":")
        print(toStr(s))
    else:
        print("Wrong positions! Positions must be between 0 and " + str(
            len(l)) + "Start position must be smaller than end position.")
        raise ValueError("Wrong positions! Positions must be between 0 and " + str(
            len(l)) + "Start position must be smaller than end position.")


def ui_product(l, x):
    x = x.split(" ")
    x[1] = int(x[1])
    x[3] = int(x[3])
    if x[1] >= 0 and x[3] >= 0 and x[1] <= x[3] and x[1] < len(l) and x[3] < len(l):
        s = DoProduct(l, x[1], x[3])
        print("product " + str(x[1]) + " to " + str(x[3]) + ":")
        print(toStr(s))
    else:
        print("Wrong positions! Positions must be between 0 and " + str(
            len(l)) + "Start position must be smaller than end position.")
        raise ValueError("Wrong positions! Positions must be between 0 and " + str(
            len(l)) + "Start position must be smaller than end position.")


def ui_filter(l, x):
    x = x.split(" ")
    if x[1] == 'real':
        print(l)
        FilterReal(l)
        print(l)
        PrintList(l, 0, len(l) - 1)
    else:
        if x[1] == 'modulo':
            x[3] = int(x[3])
            if x[2] == '>':
                l = FilterModuloGreater(l, x[3])
                PrintList(l, 0, len(l) - 1)
            elif x[2] == '<':
                l = FilterModuloSmaller(l, x[3])
                PrintList(l, 0, len(l) - 1)
            elif x[2] == '=':
                l = FilterModuloEqual(l, x[3])
                PrintList(l, 0, len(l) - 1)



def run():
    l = []
    init(l)
    undo_list = []
    sf = []
    print("This aplication helps you test properties of complex numbers.")
    while True:
        PrinMenu()
        x = input("Please give a command from the menu above:")
        try:
            if ValidADD(x) == True:
                ui_add(l, x)
                undo_list.append(l[:])
            elif ValidList(x) == True:
                ui_list(l, x)
            elif ValidRemove(x) == True:
                ui_remove(l, x)
            elif ValidInsert(x) == True:
                ui_insert(l, x)
            elif ValidReplace(x) == True:
                ui_replace(l, x)
            elif ValidSum(x) == True:
                ui_sum(l, x)
            elif ValidProduct(x) == True:
                ui_product(l, x)
            elif ValidFilter(x) == True:
                ui_filter(l, x)
            elif ValidUndo(x) == True:
                sf = ui_undo(undo_list)
                PrintList(sf, 0, len(sf) - 1)
            else:
                raise ValueError("Invalid command!Please try again!")
        except ValueError as ve:
            print("Invalid command!Please try again!")

