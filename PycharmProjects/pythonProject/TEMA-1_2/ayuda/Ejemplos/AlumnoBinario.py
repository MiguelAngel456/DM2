import pickle
from xml.etree import ElementTree as ET

class Alumno():
    def __init__(self,id,nombre,departamento):
        self.id = id
        self.nombre = nombre
        self.departamaneto = departamento

    def verAlumno(self):
        print(self.id+"---"+self.nombre+"---"+self.departamaneto)

#Escribe
with open("alumnos.pickle", "wb") as f:
    raiz = ET.parse("alumnos.xml").getroot()
    lista = raiz.findall("alumno")
    for al in lista:
        id = al.get("id")
        nombre = al.find("nombre").text
        departamento = al.find("departamento").text
        alumno = Alumno(id,nombre,departamento)
        pickle.dump(alumno,f)

#Lee
with open("alumnos.pickle", "rb") as f:
    while True:
        try:
            alumno = pickle.load(f)
            alumno.verAlumno()
        except EOFError:
            break

#Añade
alumno = Alumno("4","Andoni","administracion")
with open("alumnos.pickle", "ab") as f:
    pickle.dump(alumno, f)
print()

#Lee otra vez
with open("alumnos.pickle", "rb") as f:
    while True:
        try:
            alumno = pickle.load(f)
            alumno.verAlumno()
        except EOFError:
            break

#Busca
nom = input("Introduce nombre a buscar")
with open("alumnos.pickle", "rb") as f:
    while True:
        try:
            alumno = pickle.load(f)
            if alumno.nombre == nom:
                alumno.verAlumno()
        except EOFError:
            break
