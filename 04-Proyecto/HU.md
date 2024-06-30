# Historia de Usuario 1
    Yo, como usuario registrado, para poder iniciar conversaciones anónimas, 
## Título
    Como usuario, quiero poder generar enlaces de chat para invitar a otros usuarios a chatear de forma anónima.

### Criterios de Aceptación
    1. Debe existir una opción para generar un nuevo enlace de chat desde la pantalla principal.
    2. Los enlaces de chat generados deben ser únicos y expirar automáticamente después de 30 minutos.
    3. Los usuarios invitados deben poder acceder al chat utilizando el enlace compartido.

### ID
    US001
### Estimado
    10 puntos 

### Prototipos
![Prototipo de Generación de Enlace de Chat](https://www.figma.com/proto/e1JCaEy3UzJFcJtJKFvbYN/TxT--Prototype-Anonymous-Chat-App?node-id=3-3958&t=bJkqpCvXkjexiDMv-1)

### Reunión de Diseño (Discusión)
    En la reunión de diseño, discutimos la implementación de la generación de enlaces de chat utilizando Firestore para almacenar y gestionar los enlaces. También revisamos el flujo de expiración automática de enlaces y cómo notificar a los usuarios cuando un chat expire.

#

# Historia de Usuario 2
    Yo, como usuario registrado, para poder participar en chats anónimos, utilizando un enlace generado por otro usuario.
## Título
    Como usuario, quiero poder unirme a un chat utilizando un enlace generado por otro usuario.

## Criterios de Aceptación
    1. Debe existir una opción para ingresar un enlace de chat desde la pantalla principal.
    2. Al ingresar el enlace válido, el usuario debe ser redirigido automáticamente al chat correspondiente.
    3. Los participantes deben poder enviar y recibir mensajes en tiempo real dentro del chat.

### ID
    US002

### Estimado
    10 puntos 

### Prototipos
![Prototipo de Unirse a un Chat](https://www.figma.com/proto/e1JCaEy3UzJFcJtJKFvbYN/TxT--Prototype-Anonymous-Chat-App?node-id=3-3958&t=bJkqpCvXkjexiDMv-1)

### Reunión de Diseño (Discusión)
    En la reunión de diseño, discutimos la implementación del flujo para unirse a un chat utilizando enlaces generados. También revisamos cómo gestionar la autenticación y la seguridad para asegurar que solo los usuarios autorizados puedan acceder a los chats.


##


# Historia de Usuario 3
    Yo, como usuario registrado, para poder gestionar mis chats activos,
## Título
    Como usuario, quiero ver una lista de mis chats activos para poder administrar mis conversaciones.

### Criterios de Aceptación
    1. Debe existir una pantalla que liste todos los chats activos en los que el usuario participa.
    2. Los chats deben mostrarse ordenados según su actividad reciente.
    3. Desde la lista de chats, el usuario debe poder acceder rápidamente a cada chat para ver y enviar mensajes.

### ID
    US003

### Estimado
    10 puntos

### Prototipos
![Prototipo de Lista de Chats Activos](https://www.figma.com/proto/e1JCaEy3UzJFcJtJKFvbYN/TxT--Prototype-Anonymous-Chat-App?node-id=3-3958&t=bJkqpCvXkjexiDMv-1)

### Reunión de Diseño (Discusión)
En la reunión de diseño, discutimos la estructura de la pantalla de lista de chats activos y cómo integrar la funcionalidad con Firestore para obtener y mostrar los chats del usuario de manera eficiente.




