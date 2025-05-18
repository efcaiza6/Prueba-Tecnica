# Prueba Técnica
# Aplicación con Java 17 y MySQL

La aplicación ha sido desarrollado utilizando la tecnología Spring Boot con JDK 17 y una base de datos MySQL.

## Configuración de la Base de Datos

La aplicación está diseñada para trabajar con MySQL como base de datos, y para ello, se requieren las siguientes credenciales:

- **Usuario:** `root`
- **Contraseña:** `admin`
- **Nombre de la base de datos:** `prueba_tecnica`

Asegúrate de configurar estas credenciales correctamente en el archivo de configuración de la aplicación antes de ejecutarla para tener una conexión adecuada con la base de datos MySQL.

Se agrega el Backup de la base de datos


## Ejecutar la Aplicación con Docker Compose

Se pueden cambiar las credenciales y puertos donde se ejecutará la aplicación en el archivo `.env` ubicado en la raiz del proyecto. Esta configurado actualmente puerto ``MySQL: 3307`` y puerto de primer ms ``spring: 8081`` y del segundo ms ``spring: 8082``

Para ejecutar la aplicación, sigue estos pasos:

1. Abre una terminal en el directorio raíz de la aplicación.
2. Ejecuta el siguiente comando para construir e iniciar los contenedores:

   ```cmd
   docker-compose up --build
   ```

3. Se debe importar la data de la BD utilizando un archivo backup ubicado en el direcorio raiz