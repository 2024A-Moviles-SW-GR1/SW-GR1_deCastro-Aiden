package com.example.a2024aswgr1aldj

class BBaseDatosMemoria {
    // Companion Object
    companion object{
        val arregloBEntrenador = arrayListOf<BEntrenador>()
        init{
            arregloBEntrenador
                .add( BEntrenador(1,"Aiden","a@a.com"))
            arregloBEntrenador
                .add( BEntrenador(2,"Liam","b@b.com"))
            arregloBEntrenador
                .add( BEntrenador(3,"Leiden","c@c.com"))
        }
    }
}