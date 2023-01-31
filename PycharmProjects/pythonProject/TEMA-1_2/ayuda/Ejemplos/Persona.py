import random


class Persona:
    def __init__(self, nombre = "", edad = "", sexo = "H", peso = "", altura = ""):
        self.__nombre = nombre
        self.__edad = edad
        self.__dni = self.__generaDNI()
        self.__sexo = sexo
        self.__peso = peso
        self.__altura = altura

    def calcularIMC(self):
        resul = self.__peso/(self.__altura**2)
        if resul<20:
            return -1
        if resul>=20 and resul<=25:
            return 0
        return 1

    def esMayorDeEdad(self):
        if self.__edad>17:
            return True
        else:
            return False

    def toString(self):
        print(self.__nombre+" "+(str)(self.__edad)+" "+self.__sexo+" "+(str)(self.__peso)+" "+(str)(self.__altura)+" "+self.__dni)

    def __str__(self):
        print(self.__nombre+" "+(str)(self.__edad)+" "+self.__sexo+" "+(str)(self.__peso)+" "+(str)(self.__altura)+" "+self.__dni)


    def __generaDNI(self):
        numero = random.randint(10000000,99999999)
        letra = chr(random.randint(ord("A"),ord("Z")))
        return (str)(numero)+letra

p = Persona("Pedro",32,"M",83,1.80)
p.calcularIMC();
p.__str__()