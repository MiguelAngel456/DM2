import os
import shutil
import csv
from xml import sax
from xml.etree import ElementTree as ET
from xml.dom import minidom

from parseador import parseador


class Ejercicio3:

    def menu(self):
        num = (int)(input("""Hola, ¿qué quieres hacer hoy?
        1-Crear fichero XML de olimpiadas
        2-Crear un fichero XML de deportistas
        3-Listado de olimpiadas
        4-Salir del programa

        """))

        if num == 1:
            self.metodo1()
        elif num == 2:
            self.metodo2()
        elif num == 3:
            self.metodo3()
        else:
            print("Adio")


    def metodo1(self):
        #Crear fichero XML de olimpiadas
        olimpiadas = []
        with open("olimpiadas.csv") as csvfile: # Lee olimpiadas.csv
            reader = csv.DictReader(csvfile)
            for row in reader:
                olimpiadas.append(row)

        # Ordena de mayor a menor y de menor a mayor
        olimpiadas = sorted(olimpiadas, key=lambda x: x["Season"], reverse=True)
        olimpiadas = sorted(olimpiadas, key=lambda x: x["Year"])

        # Lo crea sin escribir
        root = ET.Element("olimpiadas")
        for o in olimpiadas:
            olimpiada = ET.Element("olimpiada")# Crea elemento sin posicion
            olimpiada.set("year", o["Year"]) # Atributo
            juegos = ET.SubElement(olimpiada, "juegos") #Crea elemento con posicion
            juegos.text = o["Games"] #Contenido del elemento
            temporada = ET.SubElement(olimpiada, "temporada")
            temporada.text = o["Season"]
            ciudad = ET.Element("ciudad")
            ciudad.text = o["City"]
            olimpiada.append(ciudad)
            root.append(olimpiada) # Añade posicion al elemento
        str = minidom.parseString(ET.tostring(root)).toprettyxml(indent="\t") #Lo pone bonito (Copia y pega)

        # Abre el xml y lo escribe
        f = open("olimpiadas.xml", "w")
        f.write(str)
        f.close
        self.menu()

    def metodo2(self):
        #Crear un fichero XML de deportistas

        olimpiadas = []
        with open("athlete_eventsPoco.csv") as csvfile:
            reader = csv.DictReader(csvfile)
            for row in reader:
                olimpiadas.append(row)
            print(len(olimpiadas))

        lista = []
        ldeporte = []

        root = ET.Element("deportistas")
        for o in olimpiadas:
            if o["Name"] not in lista:
                lista.append(o["Name"])
                deportista = ET.Element("deportista")
                deportista.set("id", o["ID"])
                root.append(deportista)

                nombre = ET.SubElement(deportista, "nombre")
                nombre.text = o["Name"]
                sexo = ET.SubElement(deportista, "sexo")
                sexo.text = o["Sex"]
                altura = ET.SubElement(deportista, "altura")
                altura.text = o["Height"]
                peso = ET.SubElement(deportista, "peso")
                peso.text = o["Weight"]
                participaciones = ET.SubElement(deportista, "participaciones")
                ldeporte.clear()


            if not o["Sport"] in ldeporte:
                ldeporte.append(o["Sport"])
                deporte = ET.SubElement(participaciones, "deporte")
                deporte.set("nombre", o["Sport"])


            participacion = ET.SubElement(deporte, "participacion")
            participacion.set("edad", o["Age"])


            equipo = ET.SubElement(participacion, "equipo")
            equipo.set("abbr", o["NOC"])
            equipo.text = o["Team"]+" - "+o["City"]
            juegos = ET.SubElement(participacion, "evento")
            juegos.text = o["Games"]
            evento = ET.SubElement(participacion, "evento")
            evento.text = o["Event"]
            medalla = ET.SubElement(participacion, "medalla")
            medalla.text = o["Medal"]

        str = minidom.parseString(ET.tostring(root)).toprettyxml(indent="\t")
        f = open("aaaaaaaa.xml", "w")
        f.write(str)
        f.close
        print(len(lista))
        print(lista)
        self.menu()

    def metodo3(self):
        #Listado de olimpiadas
        ejemp=parseador()
        sax.parse("olimpiadas.xml",ejemp)
        self.menu()

e = Ejercicio3()
e.menu()