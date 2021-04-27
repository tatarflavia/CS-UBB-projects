from repository import *
from model import *

def test_CreateComplex():
    a = 8
    b = 9
    c = CreateComplex(a, b)
    assert GetReal(c) == 8
    assert GetImg(c) == 9
    a = 7
    b = 0
    c = CreateComplex(a, b)
    assert GetReal(c) == 7
    assert GetImg(c) == 0
    SetReal(c, 4)
    SetImg(c, 4)
    assert GetReal(c) == 4
    assert GetImg(c) == 4


def test_addElToList():
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0}]
    addElToList(l, {'re': 5.0, 'im': 6.0})
    assert l == [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0},
                 {'re': 5.0, 'im': 6.0}]
    l = [{'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 0.0}, {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0}]
    addElToList(l, {'re': 5.0, 'im': 6.0})
    assert l == [{'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 0.0}, {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0},
                 {'re': 5.0, 'im': 6.0}]

def test_insertElToPos():
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0}]
    c = {'re': 5.0, 'im': 5.0}
    pos = 3
    assert insertElToPos(l, c, pos) == [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0},
                                        {'re': 5.0, 'im': 5.0}, {'re': 4.0, 'im': 0.0}]
    l = [{'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 0.0}, {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0}]
    c = {'re': 5.0, 'im': 5.0}
    pos = 0
    assert insertElToPos(l, c, pos) == [{'re': 5.0, 'im': 5.0}, {'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 0.0},
                                        {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0}]
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0},
         {'re': 6.0, 'im': 8.0}, {'re': 4.0, 'im': 7.0}, {'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 0.0},
         {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0}, {'re': 6.0, 'im': 0.0}, {'re': 9.0, 'im': 0.0},
         {'re': 5.0, 'im': 0.0}, {'re': 10.0, 'im': 0.0}, {'re': 3.0, 'im': 0.0}]
    c = {'re': 5.0, 'im': 5.0}
    pos = 3
    assert insertElToPos(l, c, pos) == [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0},
                                        {'re': 5.0, 'im': 5.0}, {'re': 4.0, 'im': 0.0}, {'re': 6.0, 'im': 8.0},
                                        {'re': 4.0, 'im': 7.0}, {'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 0.0},
                                        {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0}, {'re': 6.0, 'im': 0.0},
                                        {'re': 9.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 10.0, 'im': 0.0},
                                        {'re': 3.0, 'im': 0.0}]



def test_RemovePositions():
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0}]
    x = 0
    y = 3
    assert RemovePositions(l, x, y) == []
    l = [{'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 0.0}, {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0}]
    x = 2
    y = 3
    assert RemovePositions(l, x, y) == [{'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 0.0}]
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0},
         {'re': 6.0, 'im': 8.0}, {'re': 4.0, 'im': 7.0}, {'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 0.0},
         {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0}, {'re': 6.0, 'im': 0.0}, {'re': 9.0, 'im': 0.0},
         {'re': 5.0, 'im': 0.0}, {'re': 10.0, 'im': 0.0}, {'re': 3.0, 'im': 0.0}]
    x = 0
    y = 0
    assert RemovePositions(l, x, y) == [{'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0},
                                        {'re': 6.0, 'im': 8.0}, {'re': 4.0, 'im': 7.0}, {'re': -1.0, 'im': -2.0},
                                        {'re': 3.0, 'im': 0.0}, {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0},
                                        {'re': 6.0, 'im': 0.0}, {'re': 9.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0},
                                        {'re': 10.0, 'im': 0.0}, {'re': 3.0, 'im': 0.0}]



def test_Removepos():
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0}]
    x = 0
    assert RemovePos(l, x) == [{'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0}]
    l = [{'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 0.0}, {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0}]
    x = 3
    assert RemovePos(l, x) == [{'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 0.0}, {'re': 9.0, 'im': 8.0}]



def test_ExistEl():
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0}]
    assert ExistEl(l, {'re': 5.0, 'im': 6.0}) == True
    l = [{'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 0.0}, {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0}]
    assert ExistEl(l, {'re': 4.0, 'im': 3.0}) == False
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0},
         {'re': 6.0, 'im': 8.0}, {'re': 4.0, 'im': 7.0}, {'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 0.0},
         {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0}, {'re': 6.0, 'im': 0.0}, {'re': 9.0, 'im': 0.0},
         {'re': 5.0, 'im': 0.0}, {'re': 10.0, 'im': 0.0}, {'re': 3.0, 'im': 0.0}]
    assert ExistEl(l, {'re': 3.0, 'im': 0.0}) == True


def test_replaceEl():
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0},
         {'re': 6.0, 'im': 0.0}]

    assert replaceEl(l, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 6.0}) == [{'re': 5.0, 'im': 6.0},
                                                                            {'re': 5.0, 'im': 6.0},
                                                                            {'re': 5.0, 'im': 0.0},
                                                                            {'re': 4.0, 'im': 0.0},
                                                                            {'re': 5.0, 'im': 6.0}]
    l = [{'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 0.0}, {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0}]
    assert replaceEl(l, {'re': -1.0, 'im': -2.0}, {'re': 9.0, 'im': 8.0}) == [{'re': 9.0, 'im': 8.0},
                                                                              {'re': 3.0, 'im': 0.0},
                                                                              {'re': 9.0, 'im': 8.0},
                                                                              {'re': 6.0, 'im': 9.0}]
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0},
         {'re': 6.0, 'im': 8.0}, {'re': 4.0, 'im': 7.0}, {'re': -1.0, 'im': -2.0}, {'re': 9.0, 'im': 0.0},
         {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0}, {'re': 6.0, 'im': 0.0}, {'re': 9.0, 'im': 0.0},
         {'re': 5.0, 'im': 0.0}, {'re': 10.0, 'im': 0.0}, {'re': 3.0, 'im': 0.0}]
    assert replaceEl(l, {'re': 3.0, 'im': 0.0}, {'re': 9.0, 'im': 0.0}) == [{'re': 5.0, 'im': 6.0},
                                                                            {'re': 6.0, 'im': 0.0},
                                                                            {'re': 5.0, 'im': 0.0},
                                                                            {'re': 4.0, 'im': 0.0},
                                                                            {'re': 6.0, 'im': 8.0},
                                                                            {'re': 4.0, 'im': 7.0},
                                                                            {'re': -1.0, 'im': -2.0},
                                                                            {'re': 9.0, 'im': 0.0},
                                                                            {'re': 9.0, 'im': 8.0},
                                                                            {'re': 6.0, 'im': 9.0},
                                                                            {'re': 6.0, 'im': 0.0},
                                                                            {'re': 9.0, 'im': 0.0},
                                                                            {'re': 5.0, 'im': 0.0},
                                                                            {'re': 10.0, 'im': 0.0},
                                                                            {'re': 9.0, 'im': 0.0}]


def test_ModuloEq():
    l = [{'re': 8.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 6.0, 'im': 8.0}]
    x = 10
    assert ModuloEq(l, x) == [{'re': 8.0, 'im': 6.0}, {'re': 6.0, 'im': 8.0}]
    l = [{'re': 5.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 9.0, 'im': 8.0}, {'re': 0.0, 'im': 1.0}]
    x = 5
    assert ModuloEq(l, x) == [{'re': 5.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}]
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0},
         {'re': 6.0, 'im': 8.0}, {'re': 4.0, 'im': 7.0}, {'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 0.0},
         {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0}, {'re': 6.0, 'im': 0.0}, {'re': 9.0, 'im': 0.0},
         {'re': 5.0, 'im': 0.0}, {'re': 10.0, 'im': 0.0}, {'re': 3.0, 'im': 0.0}]
    x = 0
    assert ModuloEq(l, x) == []


def test_ModuloSm():
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0}]
    x = 100
    assert ModuloSm(l, x) == [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0},
                              {'re': 4.0, 'im': 0.0}]
    l = [{'re': 1.0, 'im': 1.0}, {'re': 3.0, 'im': 0.0}, {'re': 9.0, 'im': 8.0}, {'re': 0.0, 'im': 1.0}]
    x = 3
    assert ModuloSm(l, x) == [{'re': 1.0, 'im': 1.0}, {'re': 0.0, 'im': 1.0}]
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0},
         {'re': 6.0, 'im': 8.0}, {'re': 4.0, 'im': 7.0}, {'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 0.0},
         {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0}, {'re': 6.0, 'im': 0.0}, {'re': 9.0, 'im': 0.0},
         {'re': 5.0, 'im': 0.0}, {'re': 10.0, 'im': 0.0}, {'re': 3.0, 'im': 0.0}]
    x = 0
    assert ModuloSm(l, x) == []


def test_ModuloGr():
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0}]
    x = 0
    assert ModuloGr(l, x) == [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0},
                              {'re': 4.0, 'im': 0.0}]
    l = [{'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 0.0}, {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0}]
    x = 3
    assert ModuloGr(l, x) == [{'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0}]
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0},
         {'re': 6.0, 'im': 8.0}, {'re': 4.0, 'im': 7.0}, {'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 0.0},
         {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0}, {'re': 6.0, 'im': 0.0}, {'re': 9.0, 'im': 0.0},
         {'re': 5.0, 'im': 0.0}, {'re': 10.0, 'im': 0.0}, {'re': 3.0, 'im': 0.0}]
    x = 2000
    assert ModuloGr(l, x) == []


def test_DoSum():
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0}]
    a = 0
    b = 3
    assert DoSum(l, a, b) == {'re': 20.0, 'im': 6.0}
    l = [{'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 0.0}, {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0}]
    a = 1
    b = 2
    assert DoSum(l, a, b) == {'re': 12.0, 'im': 8.0}


def test_DoProduct():
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0}]
    a = 0
    b = 3
    assert DoProduct(l, a, b) == {'re': 600.0, 'im': 0.0}
    l = [{'re': -1.0, 'im': -2.0}, {'re': 0.0, 'im': 0.0}, {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0}]
    a = 1
    b = 1
    assert DoSum(l, a, b) == {'re': 0.0, 'im': 0.0}


def test_FilterModuloSmaller():
    l = [{'re': 4.0, 'im': 0.0}, {'re': 3.0, 'im': 0.0}, {'re': 2.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0}]
    x = 4
    assert FilterModuloSmaller(l, x) == [{'re': 3.0, 'im': 0.0}, {'re': 2.0, 'im': 0.0}]
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 9.0}, {'re': 5.0, 'im': 9.0}, {'re': 4.0, 'im': 8.0},
         {'re': 6.0, 'im': 8.0}, {'re': 4.0, 'im': 7.0}, {'re': 7.0, 'im': 0.0}]
    x = 5
    assert FilterModuloSmaller(l, x) == []
    l = [{'re': 4.0, 'im': 6.0}, {'re': 6.0, 'im': 8.0}, {'re': 5.0, 'im': 9.0}, {'re': 4.0, 'im': 8.0},
         {'re': 4.0, 'im': 7.0}, {'re': -1.0, 'im': -2.0}, {'re': 10.0, 'im': 9.0}, {'re': 9.0, 'im': 8.0},
         {'re': 6.0, 'im': 9.0}, {'re': 6.0, 'im': 8.0}, {'re': 9.0, 'im': 8.0}, {'re': 5.0, 'im': 9.0},
         {'re': 4.0, 'im': 8.0}, {'re': 4.0, 'im': 8.0}]
    x = 10
    assert FilterModuloSmaller(l, x) == [{'re': 4.0, 'im': 6.0}, {'re': 4.0, 'im': 8.0}, {'re': 4.0, 'im': 7.0},
                                         {'re': -1.0, 'im': -2.0}, {'re': 4.0, 'im': 8.0}, {'re': 4.0, 'im': 8.0}]



def test_FilterModuloEqual():
    l = [{'re': 4.0, 'im': 0.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0}]
    x = 4
    assert FilterModuloEqual(l, x) == [{'re': 4.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0}]
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 9.0}, {'re': 5.0, 'im': 9.0}, {'re': 4.0, 'im': 8.0},
         {'re': 6.0, 'im': 8.0}, {'re': 4.0, 'im': 7.0}, {'re': 4.0, 'im': 0.0}]
    x = 5
    assert FilterModuloEqual(l, x) == []
    l = [{'re': 8.0, 'im': 6.0}, {'re': 6.0, 'im': 8.0}, {'re': 5.0, 'im': 9.0}, {'re': 4.0, 'im': 8.0},
         {'re': 4.0, 'im': 7.0}, {'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 9.0}, {'re': 9.0, 'im': 8.0},
         {'re': 6.0, 'im': 9.0}, {'re': 6.0, 'im': 8.0}, {'re': 9.0, 'im': 8.0}, {'re': 5.0, 'im': 9.0},
         {'re': 6.0, 'im': 8.0}, {'re': 6.0, 'im': 8.0}]
    x = 10
    assert FilterModuloEqual(l, x) == [{'re': 8.0, 'im': 6.0}, {'re': 6.0, 'im': 8.0}, {'re': 6.0, 'im': 8.0},
                                       {'re': 6.0, 'im': 8.0}, {'re': 6.0, 'im': 8.0}]


def test_FilterModuloGreater():
    l = [{'re': 8.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0}]
    x = 5
    assert FilterModuloGreater(l, x) == [{'re': 8.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}]
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 9.0}, {'re': 5.0, 'im': 9.0}, {'re': 4.0, 'im': 8.0},
         {'re': 6.0, 'im': 8.0}, {'re': 4.0, 'im': 7.0}, {'re': 4.0, 'im': 0.0}]
    x = 200
    assert FilterModuloGreater(l, x) == []
    l = [{'re': 100.0, 'im': 60.0}, {'re': 6.0, 'im': 9.0}, {'re': 5.0, 'im': 9.0}, {'re': 4.0, 'im': 8.0},
         {'re': 6.0, 'im': 8.0}, {'re': 4.0, 'im': 7.0}, {'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 9.0},
         {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0}, {'re': 6.0, 'im': 8.0}, {'re': 9.0, 'im': 8.0},
         {'re': 5.0, 'im': 9.0}, {'re': 10.0, 'im': 8.0}, {'re': 100.0, 'im': 8.0}]
    x = 20
    assert FilterModuloGreater(l, x) == [{'re': 100.0, 'im': 60.0}, {'re': 100.0, 'im': 8.0}]



def test_FilterReal():
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0}]
    FilterReal(l)
    assert l == [{'re': 6.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0}]
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 9.0}, {'re': 5.0, 'im': 9.0}, {'re': 4.0, 'im': 8.0},
         {'re': 6.0, 'im': 8.0}, {'re': 4.0, 'im': 7.0}, {'re': 4.0, 'im': 0.0}]
    FilterReal(l)
    assert l == [{'re': 4.0, 'im': 0.0}]
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 9.0}, {'re': 5.0, 'im': 9.0}, {'re': 4.0, 'im': 8.0},
         {'re': 6.0, 'im': 8.0}, {'re': 4.0, 'im': 7.0}, {'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 9.0},
         {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0}, {'re': 6.0, 'im': 8.0}, {'re': 9.0, 'im': 8.0},
         {'re': 5.0, 'im': 9.0}, {'re': 10.0, 'im': 8.0}, {'re': 3.0, 'im': 8.0}]
    FilterReal(l)
    assert l == []
    l = [{'re': 5.0, 'im': 0.0}, {'re': 6.0, 'im': 9.0}, {'re': 5.0, 'im': 9.0}, {'re': 4.0, 'im': 0.0},
         {'re': 6.0, 'im': 8.0}, {'re': 4.0, 'im': 7.0}, {'re': -1.0, 'im': -2.0}, {'re': 3.0, 'im': 9.0},
         {'re': 9.0, 'im': 8.0}, {'re': 6.0, 'im': 9.0}, {'re': 6.0, 'im': 0.0}, {'re': 9.0, 'im': 0.0},
         {'re': 5.0, 'im': 0.0}, {'re': 10.0, 'im': 8.0}, {'re': 3.0, 'im': 0.0}]
    FilterReal(l)
    assert l == [{'re': 5.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0}, {'re': 6.0, 'im': 0.0},
                             {'re': 9.0, 'im': 0.0}, {'re': 5.0, 'im': 0.0}, {'re': 3.0, 'im': 0.0}]
    l = [{'re': 5.0, 'im': 6.0}, {'re': 6.0, 'im': 0.0}, {'re': 6.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0}]
    FilterReal(l)
    assert l == [{'re': 6.0, 'im': 0.0}, {'re': 6.0, 'im': 0.0}, {'re': 4.0, 'im': 0.0}]


def run_tests():
    test_addElToList()
    test_CreateComplex()
    test_insertElToPos()
    test_ModuloGr()
    test_ModuloSm()
    test_DoSum()
    test_DoProduct()
    test_RemovePositions()
    test_ExistEl()
    test_replaceEl()
    test_Removepos()
    test_FilterReal()
    test_FilterModuloGreater()
    test_FilterModuloEqual()
    test_FilterModuloSmaller()
    test_ModuloEq()

