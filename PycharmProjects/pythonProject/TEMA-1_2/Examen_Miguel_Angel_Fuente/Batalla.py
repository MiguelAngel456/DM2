
class Batalla:
    def __init__(self,id,nombre,anio,region,localizacion,reyAtac,reyDef,ganaAtac):
        self.id=id
        self.nombre=nombre
        self.anio=anio
        self.region=region
        self.localizacion=localizacion
        self.reyAtac=reyAtac
        self.reyDef=reyDef
        self.gana=ganaAtac
    def __str__(self):
        return "The ",self.nombre," took place in ",self.localizacion,"(",self.region,") in the year ",self.anio,". The king ",self.reyAtac," fought against ",self.reyDef," and he/they ",self.gana