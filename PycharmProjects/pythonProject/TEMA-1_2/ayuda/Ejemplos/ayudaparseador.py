import xml.sax as sax

class ayudaparseador(sax.handler.ContentHandler):
   def __init__(self):
       self.currentdata=""

   def startElement(self,etiqueta,atributo):
       self.currentdata=etiqueta
       if etiqueta=="olimpiada":
           print(atributo["year"])
       if etiqueta == "ejemplo":
           print(atributo["prueba"])
   def endElement(self,tal):
       self.currentdata=""
   def characters(self,contrent):
       if self.currentdata=="juegos":
           print(contrent)
       if self.currentdata == "ciudad":
           print(contrent)