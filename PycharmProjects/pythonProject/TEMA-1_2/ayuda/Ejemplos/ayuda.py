import os
import shutil
import csv
from xml import sax
from xml.etree import ElementTree as ET
from xml.dom import minidom

from ayudaparseador import ayudaparseador


class Ejercicio3:

    def menu(self):
        num = (int)(input("""Soy ayuda dale a 3

        """))


        if num == 3:
            self.metodo3()
        else:
            print("Adio")


    def metodo3(self):
        ejemp= ayudaparseador()
        sax.parse("ayuda.xml", ejemp)
        self.menu()

e = Ejercicio3()
e.menu()