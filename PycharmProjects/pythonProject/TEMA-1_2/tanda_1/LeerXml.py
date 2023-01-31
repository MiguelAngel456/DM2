import xml.sax
import xml.etree.ElementTree as ET

raiz = ET.parse("olimpiada.xml").getroot()
lista = raiz.findall("Olimpiada")
for l in lista:
   print(l.get("Year"))
   print(l.find("juegos").text)
   print(l.find("Temporada").text)
   print(l.find("Ciudad").text)
