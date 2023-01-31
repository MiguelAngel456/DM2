from sqlalchemy import create_engine, Integer,  Column, String
from sqlalchemy.dialects.postgresql import ENUM
from sqlalchemy.orm import declarative_base, sessionmaker

engine = create_engine("mysql+pymysql://admin:password@localhost/olimpiadas")#, echo=True
Base = declarative_base()


class Olimpiada(Base):
    __tablename__="Olimpiada"
    id_olimpiada=Column(Integer, primary_key=True)
    nombre=Column(String(30))
    anio = Column(Integer)
    temporada=Column(ENUM('Summer', 'Winter'))
    ciudad = Column(String(30))


Session = sessionmaker(bind=engine)
sesion = Session()
lista = sesion.query(Olimpiada).all()
for o in lista:
    print(o.id_olimpiada,"       ",o.nombre,"       ",o.anio,"      ",o.temporada,"         ",o.ciudad)
sesion.close()


