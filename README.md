### Prerequisitos

* Java 21
* Docker

### Inicializando el proyecto

El proyecto utiliza JOOQ para relacionarse con la base de datos y generar las entidades. Dentro de este flujo
se lanza una MySQL mediante TestContainers para generar las clases de las tablas que aparezcan en la 
base de datos. (Es posible que se necesite marcar el directorio de `target/generated-sources/jooq` como Sources Root
para que pueda importar las clases autogeneradas)

```sh
make install
```
Después de haber lanzado el anterior comando ya podemos ejecutar la aplicación con el siguiente comando:

```shell
make run
```

### Peticiones de ejemplo

Petición que encuentra un precio con los parámetros aportados
```shell
curl --location 'http://localhost:8080/api/v1/prices?applicationDate=2020-06-14T10%3A00%3A00&productId=35455&brandId=1'
```
Petición que no encuentra un precio con los parámetros aportados
```shell
curl --location 'http://localhost:8080/api/v1/prices?applicationDate=2020-06-14T10%3A00%3A00&productId=21312312&brandId=1'
```
