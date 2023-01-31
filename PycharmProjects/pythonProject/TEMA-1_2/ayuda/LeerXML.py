import xml.sax
import xml.etree.ElementTree as ET

raiz = ET.parse("olimpiadas.xml").getroot()
lista = raiz.findall("olimpiada")
for l in lista:
    print(l.get("year"))
    print(l.find("juegos").text)
    print(l.find("temporada").text)
    print(l.find("ciudad").text)


