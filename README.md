# MasterChefAPI

[![CI - Build and Test](https://github.com/AlejandroHenao2572/MasterChefAPI/actions/workflows/ci.yml/badge.svg)](https://github.com/AlejandroHenao2572/MasterChefAPI/actions/workflows/ci.yml)
[![Code Quality Check](https://github.com/AlejandroHenao2572/MasterChefAPI/actions/workflows/code-quality.yml/badge.svg)](https://github.com/AlejandroHenao2572/MasterChefAPI/actions/workflows/code-quality.yml)
[![codecov](https://codecov.io/gh/AlejandroHenao2572/MasterChefAPI/branch/develop/graph/badge.svg)](https://codecov.io/gh/AlejandroHenao2572/MasterChefAPI)

API REST para la gestión de recetas de MasterChef Celebrity, desarrollada con Spring Boot y MongoDB.

## 🚀 Características

- API REST completa para gestión de recetas
- Soporte para diferentes tipos de recetas (Chef, Participante, Viewer)
- Documentación automática con Swagger/OpenAPI
- Base de datos MongoDB
- Pruebas unitarias con JUnit 5
- Cobertura de código con JaCoCo
- CI/CD con GitHub Actions

## 🛠️ Tecnologías

- **Java 21**
- **Spring Boot 3.5.6**
- **Spring Data MongoDB**
- **Maven**
- **JUnit 5**
- **JaCoCo** (Cobertura de código)
- **Swagger/OpenAPI 3**
- **Lombok**

## 📋 Prerrequisitos

- Java 21 o superior
- Maven 3.6+
- MongoDB 7.0+

## 🚀 Instalación y Ejecución

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/AlejandroHenao2572/MasterChefAPI.git
   cd MasterChefAPI
   ```

2. **Configurar MongoDB**
   ```bash
   # Iniciar MongoDB localmente
   mongod --dbpath /data/db
   ```

3. **Ejecutar la aplicación**
   ```bash
   mvn spring-boot:run
   ```

4. **Acceder a la documentación API**
   - Swagger UI: http://localhost:8080/swagger-ui.html
   - OpenAPI JSON: http://localhost:8080/v3/api-docs

## 🧪 Pruebas

### Ejecutar todas las pruebas
```bash
mvn test
```

### Generar reporte de cobertura
```bash
mvn test jacoco:report
```

El reporte se genera en `target/site/jacoco/index.html`

### Verificar umbrales de cobertura
```bash
mvn jacoco:check
```

## 🔄 CI/CD

El proyecto incluye pipelines de GitHub Actions que se ejecutan automáticamente:

### Pipeline de CI (`ci.yml`)
Se ejecuta en:
- Push a la rama `develop`
- Pull requests a la rama `develop`

**Pasos:**
1. ✅ Checkout del código
2. ☕ Configuración de JDK 21
3. 📦 Cache de dependencias Maven
4. 🗄️ Configuración de MongoDB
5. 🧪 Ejecución de pruebas
6. 📊 Generación de reporte JaCoCo
7. 📈 Subida de cobertura a Codecov
8. 💬 Comentario de cobertura en PR
9. 📋 Archivo de resultados de pruebas
10. ✔️ Verificación de umbrales de cobertura

### Pipeline de Calidad de Código (`code-quality.yml`)
Se ejecuta en:
- Push a las ramas `develop` y `main`
- Pull requests a las ramas `develop` y `main`

**Pasos:**
1. ✅ Checkout del código
2. ☕ Configuración de JDK 21
3. 🔍 Análisis con Checkstyle
4. 🐛 Análisis con SpotBugs
5. 🏗️ Verificación de construcción

## 📊 Cobertura de Código

El proyecto mantiene un umbral mínimo de **80% de cobertura** de líneas de código. El reporte detallado se genera automáticamente en cada ejecución de pruebas.

## 🏗️ Estructura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   └── com/masterchef/MasterChefAPI/
│   │       ├── config/          # Configuraciones
│   │       ├── controller/      # Controladores REST
│   │       ├── exception/       # Manejo de excepciones
│   │       ├── model/          # Modelos de datos
│   │       └── service/        # Lógica de negocio
│   └── resources/
│       └── application.properties
└── test/
    ├── java/
    │   └── com/masterchef/MasterChefAPI/
    │       └── model/          # Pruebas unitarias
    └── resources/
        └── application-test.properties
```

## 🔧 Configuración

### Variables de Entorno

| Variable | Descripción | Valor por defecto |
|----------|-------------|-------------------|
| `SPRING_DATA_MONGODB_URI` | URI de conexión a MongoDB | `mongodb://localhost:27017/masterchef` |
| `SPRING_PROFILES_ACTIVE` | Perfil activo de Spring | `dev` |

### Perfiles de Spring

- **default**: Configuración para desarrollo
- **test**: Configuración para pruebas unitarias
- **prod**: Configuración para producción

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request a la rama `develop`

### Estándares de Calidad

- ✅ Todas las pruebas deben pasar
- 📊 Cobertura mínima del 80%
- 🔍 Código debe pasar Checkstyle y SpotBugs
- 📝 Pull requests deben incluir descripción detallada

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## 👥 Autores

- **Alejandro Henao** - *Desarrollo inicial* - [AlejandroHenao2572](https://github.com/AlejandroHenao2572)

## 📞 Soporte

Si tienes alguna pregunta o problema, por favor:
1. Revisa los [Issues existentes](https://github.com/AlejandroHenao2572/MasterChefAPI/issues)
2. Crea un nuevo Issue si no encuentras solución
3. Contacta al equipo de desarrollo