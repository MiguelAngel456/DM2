from sqlalchemy.orm import declarative_base, relationship
from sqlalchemy import Integer, Column, String, ForeignKey, PrimaryKeyConstraint

Base = declarative_base()


# CREAR LA CLASE DEPORTE******************************************************************
class Deporte(Base):
    __tablename__ = 'Deporte'
    id_deporte = Column(Integer, primary_key=True)
    nombre = Column(String)


# CREAR LA CLASE DEPORTISTA******************************************************************
class Deportista(Base):
    __tablename__ = 'Deportista'
    id_deportista = Column(Integer, primary_key=True)
    nombre = Column(String)
    sexo = Column(String)
    peso = Column(Integer)
    altura = Column(Integer)


# CREAR LA CLASE EQUIPO******************************************************************
class Equipo(Base):
    __tablename__ = 'Equipo'
    id_Equipo = Column(Integer, primary_key=True)
    nombre = Column(String)
    Iniciales = Column(String)


# CREAR LA CLASE OLIMPIADA******************************************************************
class Olimpiada(Base):
    __tablename__ = 'Olimpiada'
    id_olimpiada = Column(Integer, primary_key=True)
    nombre = Column(String)
    anio = Column(Integer)
    temporada = Column(String)
    ciudad = Column(String)


# CREAR LA CLASE EVENTO******************************************************************
class Evento(Base):
    __tablename__ = 'Evento'
    id_evento = Column(Integer, primary_key=True)
    nombre = Column(String)
    id_Olimpiada = Column(Integer, ForeignKey(Olimpiada.id_olimpiada))
    customer = relationship("Olimpiada", back_populates="Evento")
    id_deporte = Column(Integer, ForeignKey(Deporte.id_deporte))
    customer = relationship("Deporte", back_populates="Evento")

# CREAR LA CLASE PARTICIPACION******************************************************************
class Participacion(Base):
    __tablename__ = 'Participacion'

    id_deportista = Column(Integer, ForeignKey(Deportista.id_deportista))
    id_evento=Column(Integer, ForeignKey(Evento.id_evento))
    __table_args__ = (
        PrimaryKeyConstraint('id_deportista', 'id_evento'),
        {},
    )



    nombre = Column(String)
    id_Olimpiada = Column(Integer, ForeignKey(Olimpiada.id_olimpiada))
    customer = relationship("Olimpiada", back_populates="Evento")
    id_deporte = Column(Integer, ForeignKey(Deporte.id_deporte))
    customer = relationship("Deporte", back_populates="Evento")
