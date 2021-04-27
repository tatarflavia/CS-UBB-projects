from console import *


def ValidInsert(x):
    if x == 'insert':
        return False
    else:
        x = x.split(" ")
        if len(x) != 4:
            return False
        else:
            if x[0] == 'insert':
                a, b = x[1].split("+")
                b = b.split("i")
                b = b[0]
                a = int(a)
                b = int(b)
                x[3] = int(x[3])
                if isinstance(x[3], int) and x[2] == 'at' and isinstance(a, int) and isinstance(b, int):
                    return True
                else:
                    return False
            else:
                return False


def ValidADD(x):
    if x == 'add':
        return False
    else:
        x = x.split(" ")
        if x[0] == 'add':
            a, b = x[1].split("+")
            b = b.split("i")
            b = b[0]
            a = int(a)
            b = int(b)
            if isinstance(a, int) and isinstance(b, int):
                return True
            else:
                return False


def ValidReplace(x):
    if x == 'replace':
        return False
    else:
        x = x.split(" ")
        if x[0] == 'replace':
            a1, b1 = x[1].split("+")
            b1 = b1.split("i")
            b1 = b1[0]
            a1 = int(a1)
            b1 = int(b1)
            a2, b2 = x[3].split("+")
            b2 = b2.split("i")
            b2 = b2[0]
            a2 = int(a2)
            b2 = int(b2)
            if isinstance(a1, int) and isinstance(a2, int) and isinstance(b1, int) and isinstance(b2, int) and x[
                2] == 'with':
                return True
            else:
                return False
        else:
            return False


def ValidRemove(x):
    if x == 'remove':
        return False
    else:
        x = x.split(" ")
        if len(x) == 2:
            if x[0] == 'remove':
                x[1] = int(x[1])
                if isinstance(x[1], int):
                    return True
                else:
                    return False
            else:
                return False
        elif len(x) == 4:
            if x[0] == 'remove':
                x[1] = int(x[1])
                x[3] = int(x[3])
                if isinstance(x[1], int) and x[2] == 'to' and isinstance(x[3], int):
                    return True
                else:
                    return False
            else:
                return False

        else:
            return False



def ValidList(x):
    if x == 'list':
        return True
    else:
        x = x.split(" ")
        if x[0] == 'list':
            if len(x) == 5:

                if x[1] == 'real':
                    x[2] = int(x[2])
                    x[4] = int(x[4])
                    if isinstance(x[2], int) and x[3] == 'to' and isinstance(x[4], int):
                        return True
                    else:
                        return False
            elif len(x) == 4:
                if x[1] == 'modulo':
                    if x[2] == '>' or x[2] == '<' or x[2] == '=':
                        x[3] = int(x[3])
                        if isinstance(x[3], int):
                            return True
                        else:
                            return False
                    else:
                        return False
                else:
                    return False


def ValidSum(x):
    if x == 'sum':
        return False
    else:
        x = x.split(" ")
        if len(x) == 4:
            if x[0] == 'sum':
                x[1] = int(x[1])
                x[3] = int(x[3])
                if isinstance(x[1], int) and x[2] == 'to' and isinstance(x[3], int):
                    return True
                else:
                    return False
            else:
                return False

        else:
            return False


def ValidProduct(x):
    if x == 'product':
        return False
    else:
        x = x.split(" ")
        if len(x) == 4:
            if x[0] == 'sum':
                x[1] = int(x[1])
                x[3] = int(x[3])
                if isinstance(x[1], int) and x[2] == 'to' and isinstance(x[3], int):
                    return True
                else:
                    return False
            else:
                return False

        else:
            return False


def ValidFilter(x):
    if x == 'filter':
        return False
    else:
        x = x.split(" ")
        if len(x) == 2:
            if x[0] == 'filter':
                if x[1] == 'real':
                    return True
                else:
                    return False
            else:
                return False
        elif len(x) == 4:
            if x[0] == 'filter':
                if x[1] == 'modulo':
                    if x[2] == '>' or x[2] == '<' or x[2] == '=':
                        x[3] = int(x[3])
                        if isinstance(x[3], int):
                            return True
                        else:
                            return False
                    else:
                        return False
                else:
                    return False
            else:
                return False
        else:
            return False


def ValidUndo(x):
    if x == 'undo':
        return True
    else:
        return False
