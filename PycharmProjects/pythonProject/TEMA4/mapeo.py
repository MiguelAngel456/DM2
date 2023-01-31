from sqlalchemy import Column, Integer, String, ForeignKey, create_engine
from sqlalchemy.orm import declarative_base, relationship

Base = declarative_base()

# CREAR LA CLASE ALUMNO******************************************************************
class Alumno(Base):
    __tablename__ = 'Alumno'
    id_Alumno = Column(Integer, primary_key=True)
    nombre = Column(String(30))


# CREAR LA CLASE ALUMNO******************************************************************
class Nota(Base):
    __tablename__ = 'Nota'
    id_Nota = Column(Integer, primary_key=True)
    modulo = Column(String(30))
    nota = Column(Integer)
    id_alumno = Column(Integer, ForeignKey(Alumno.id_Alumno))
    alumno = relationship("Alumno", back_populates="notas")
Alumno.notas = relationship("Nota", back_populates="alumno")
engine = create_engine("mysql+pymysql://admin:password@localhost/instituto", echo=True)
# PARA QUE SE CREE TODOO ******************************************************************
Base.metadata.create_all(engine)
