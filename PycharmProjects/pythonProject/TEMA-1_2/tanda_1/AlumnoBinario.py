from xml.etree import cElementTree as ET
import pickle

class Alumno():
    def __init__(self,id,nombre,departamento):
        self.id=id
        self.nombre=nombre
        self.departamento=departamento

    def verALumno(self):
        print(self.id+"----"+self.nombre+"-------"+self.departamento)
#ESCRIBIR
with open("alumnos.pickle", "wb") as f:
    raiz = ET.parse("ALumno.xml").getroot()
    lista = raiz.findall("Alumno")
    for al in lista:
        id=al.get("id")
        nombre=al.find("Nombre").text
        departamento = al.find("Departamento").text
        alumno = Alumno(id,nombre,departamento)
        pickle.dump(alumno,f)
#LEER
with open("alumnos.pickle", "rb") as f:
    while True:
        try:
            alumno=pickle.load(f)
            alumno.verALumno()
        except EOFError:
            break
#INSERTAR
alumno=Alumno("4","Andoni","administracion")
with open("alumnos.pickle", "ab") as f:
    pickle.dump(alumno, f)
print()
with open("alumnos.pickle", "rb") as f:
    while True:
        try:
            alumno=pickle.load(f)
            alumno.verALumno()
        except EOFError:
            break
nom=input("introduce nombre a buscar")
with open("alumnos.pickle", "rb") as f:
    while True:
        try:
            alumno=pickle.load(f)
            if alumno.nombre==nom:
                alumno.verALumno()
        except EOFError:
            break
