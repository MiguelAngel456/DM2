import csv
from xml.etree import ElementTree as ET
from xml.dom import minidom
import xml.sax as sax

from tanda_1.parseador import parseador

#FICHEROS XML

class Ejercicio3:
    def menu(self):
        menu=input("""Elige una opcion
            1.-Crear fichero XML de olimpiadas
            2.-Crear un fichero XML de deportistas
            3.-Listado de olimpiadas""")

        if menu==1:
            self.opc1()




    def opc1(self):

        with open("Olimpiadas.csv") as Olimpiadas:
            reader = csv.DictReader(Olimpiadas)
            lista = []
            for row in reader:
                lista.append(row)
            lista = sorted(lista, key=lambda x: x['Season'],reverse=True)
            lista=sorted(lista, key=lambda x:x['Year'])

        root = ET.Element("olimpiadas")#GENERAR RAIZ
        for l in lista:
                año= ET.Element("Olimpiada")
                año.set("Year",l['Year'])
                juego=ET.SubElement(año,"juegos")
                juego.text=l['Games']
                Temporada = ET.SubElement(año, "Temporada")
                Temporada.text = l['Season']
                Ciudad = ET.SubElement(año, "Ciudad")
                Ciudad.text = l['City']
                root.append(año)
        str=minidom.parseString(ET.tostring(root)).toprettyxml(indent="\t")
        f=open("olimpiada.xml", "w")
        f.write(str)
        f.close()
    def opc2(self):
        athletas=[]
        root = ET.Element("deportistas")  # GENERAR RAIZ
        with open("athlete_event3.csv") as csvFile:
            reader = csv.DictReader(csvFile)
            for row in reader:
                if row['Name'] not in athletas:
                    athletas.append(row['Name'])
                    id= ET.Element("deportista")
                    id.set("ID",  row['ID'])
                    nombre = ET.SubElement(id, "nombre")
                    nombre.text = row['Name']
                    sexo = ET.SubElement(id, "sexo")
                    sexo.text = row['Sex']
                    altura = ET.SubElement(id, "altura")
                    altura.text = row['Height']
                    peso = ET.SubElement(id, "peso")
                    peso.text = row['Weight']
                    root.append(id)
                    deportes = []
                    rootParticipaciones = ET.Element("participaciones")
                    id.append(rootParticipaciones)
                #####


                if row['Sport'] not in deportes:
                    deportes.append(row['Sport'])
                    rootDep = ET.Element("deporte")
                    rootDep.set("nombre",row['Sport'])
                    rootParticipaciones.append(rootDep)

                rootParticipacion = ET.Element("participacion")###
                anoPart= ET.Element("participacion")
                anoPart.set("edad",row['Age'])
                equipo=ET.SubElement(anoPart,"equipo")
                equipo.set("abbr",row['NOC'])
                equipo.text=row['Team']
                juegos = ET.SubElement(anoPart, "equipo")
                juegos.text=row['Games']+"-"+row['City']
                evento=ET.SubElement(anoPart,"evento")
                evento.text=row['Event']
                medalla=ET.SubElement(anoPart,"medalla")
                medalla.text=row['Medal']
                rootParticipacion.append(anoPart)

                rootDep.append(rootParticipacion)




            str = minidom.parseString(ET.tostring(root)).toprettyxml(indent="\t")
            f = open("olimpiada2.xml", "w")
            f.write(str)
            f.close()
    def opc3(self):
        ejemp=parseador()
        sax.parse("olimpiada.xml",ejemp)




j = Ejercicio3()
j.opc3()