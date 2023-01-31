from sqlalchemy import *
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

engine = create_engine('sqlite:///olimpiadas.db', echo=True)
Session = sessionmaker(bind=engine)
session = Session()

Base = declarative_base()

class Deporte (Base):
    __tablename__ = 'Deporte'

    id_deporte=Column(Integer,primary_key=True)
    nombre = Column(String)

    def __init__(self, nombre):
        self.nombre = nombre

    def __str__(self):
        return self.nombre

futbol = Deporte("futbol")
session.add(futbol)
tennis = Deporte("Tennis")
session.add(tennis)
session.commit()
print(tennis.id_deporte)
print(futbol.id_deporte)