#  Master Chef API - DOSW Company

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-brightgreen?style=for-the-badge&logo=spring)
![MongoDB](https://img.shields.io/badge/MongoDB-Atlas-green?style=for-the-badge&logo=mongodb)
![Azure](https://img.shields.io/badge/Azure-Deployed-blue?style=for-the-badge&logo=microsoftazure)



API REST para la gestión de recetas de cocina desarrollada para el programa MasterChef. Permite a participantes, chefs y televidentes registrar, consultar y gestionar recetas de cocina de manera interactiva.

---

## Tabla de Contenidos

- [Descripción del Proyecto](#-descripción-del-proyecto)
- [Tecnologías Utilizadas](#-tecnologías-utilizadas)
- [Características Principales](#-características-principales)
- [Arquitectura](#-arquitectura)
- [Instalación y Ejecución Local](#-instalación-y-ejecución-local)
- [Configuración](#️-configuración)
- [Endpoints de la API](#-endpoints-de-la-api)
- [Ejemplos de Request y Response](#-ejemplos-de-request-y-response)
- [Documentación Swagger](#-documentación-swagger)
- [Testing](#-testing)
- [CI/CD](#-cicd)

---

## Descripción del Proyecto

**Recipe Management API** es una API REST desarrollada para DOSW Company como parte de un proyecto para un programa de telerrealidad de cocina. La aplicación permite:

- **Televidentes**: Compartir sus propias recetas
- **Participantes**: Registrar recetas del programa con información de temporada
- **Chefs jurados**: Publicar recetas profesionales

### Contexto

Un importante programa de telerrealidad de cocina necesita un sitio web donde los espectadores puedan:
- Consultar recetas que han aparecido en las temporadas del programa
- Aprender y replicar las recetas en casa
- Contribuir con sus propias recetas de manera interactiva

Cada receta incluye:
- Título descriptivo
- Lista de ingredientes
- Pasos de preparación detallados
- Nombre del chef (participante, jurado o televidente)
- Información de temporada (para participantes)
- Número consecutivo único
- Timestamps de creación y actualización

---

## Tecnologías Utilizadas

### Backend
- **Java 21** - Lenguaje de programación principal
- **Spring Boot 3.3.0** - Framework de desarrollo
- **Maven** - Gestión de dependencias y build

### Base de Datos
- **MongoDB Atlas** - Base de datos NoSQL en la nube

### Documentación
- **SpringDoc OpenAPI 3** - Generación automática de documentación
- **Swagger UI** - Interfaz interactiva para la API

### Testing
- **JUnit 5** - Framework de testing
- **Mockito** - Mocking para tests unitarios
- **Spring Boot Test** - Testing de integración
- **Jacoco** - Covertura de pruebas unitarias

### DevOps
- **GitHub Actions** - CI/CD pipelines
- **Azure App Service** - Hosting en la nube

### Herramientas Adicionales
- **Lombok** - Reducción de código

---

## Características Principales

### Funcionalidades Implementadas

1. **Registrar receta de televidente**
2. **Registrar receta de participante** (con temporada)
3. **Registrar receta de chef**
4. **Obtener todas las recetas**
5. **Obtener receta por número consecutivo**
6. **Filtrar recetas de participantes**
7. **Filtrar recetas de televidentes**
8. **Filtrar recetas de chefs**
9. **Obtener recetas por temporada**
10. **Buscar recetas por ingrediente**
11. **Eliminar receta**
12. **Actualizar receta**

---

## Instalación y Ejecución Local

### Prerrequisitos

Antes de comenzar, asegúrate de tener instalado:

- **Java 21** o superior 
- **Maven 3.8+** 
- **MongoDB Atlas** 
- **Git** 
- Un editor de código (recomendado: IntelliJ IDEA, VS Code)

### Verificar Instalaciones

```bash
# Verificar Java
java -version
# Salida esperada: openjdk version "21.x.x"

# Verificar Maven
mvn -version
# Salida esperada: Apache Maven 3.8.x o superior

# Verificar Git
git --version
# Salida esperada: git version 2.x.x
```

### Pasos de Instalación

#### 1. Clonar el Repositorio

```bash
git clone https://github.com/AlejandroHenao2572/recipe-management-api.git
cd recipe-management-api
```

#### 2. Configurar MongoDB Atlas

1. Ve a [MongoDB Atlas](https://cloud.mongodb.com/)
2. Crea un cluster gratuito 
3. Haz clic en **"Connect"** → **"Connect your application"**
4. Copia la cadena de conexión:
   ```
   mongodb+srv://<username>:<password>@<cluster>.mongodb.net/recipedb?retryWrites=true&w=majority
   ```
5. En **"Network Access"**, agrega tu IP
6. Editar `application.properties`
    ```properties
    # src/main/resources/application.properties
    spring.data.mongodb.uri=mongodb+srv://username:password@cluster.mongodb.net/recipedb?retryWrites=true&w=majority
    ```

**Importante:** Reemplaza `username`, `password` y `cluster` con tus credenciales reales.

#### 4. Instalar Dependencias

```bash
mvn clean install
```

#### 5. Ejecutar la Aplicación

```bash
mvn spring-boot:run
```

La aplicación estará disponible en: **http://localhost:8080**

#### 6. Verificar que Funciona

Abre tu navegador y ve a:
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/api-docs

---

## Endpoints de la API

### Base URL

- **Local**: `http://localhost:8080/api/recipes`
- **Producción**: `https://recipe-api-doswcompany.azurewebsites.net/api/recipes`

### Resumen de Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `POST` | `/viewer` | Registrar receta de televidente |
| `POST` | `/contestant` | Registrar receta de participante |
| `POST` | `/chef` | Registrar receta de chef |
| `GET` | `/` | Obtener todas las recetas |
| `GET` | `/{consecutiveNumber}` | Obtener receta por número |
| `GET` | `/contestant` | Obtener recetas de participantes |
| `GET` | `/viewer` | Obtener recetas de televidentes |
| `GET` | `/chef` | Obtener recetas de chefs |
| `GET` | `/season/{season}` | Obtener recetas por temporada |
| `GET` | `/search?ingredient={ingrediente}` | Buscar por ingrediente |
| `PUT` | `/{consecutiveNumber}` | Actualizar receta |
| `DELETE` | `/{consecutiveNumber}` | Eliminar receta |

---

## Ejemplos de Request y Response por Endpoint

### 1. Registrar Receta de Televidente

**Endpoint:** `POST /api/recipes/viewer`:  
![alt text](images/image-1.png)
![alt text](images/image-1.png)  

**Ejemplo:**
![alt text](images/image-2.png)
![alt text](images/image-3.png)
---

### 2. Registrar Receta de Participante

**Endpoint:** `POST /api/recipes/contestant`
![alt text](images/image-6.png)  
![alt text](images/image-7.png)  

**Ejemplo:**
![alt text](images/image-4.png)  
![alt text](images/image-5.png)  
---

### 3. Registrar Receta de Chef

**Endpoint:** `POST /api/recipes/chef`
![alt text](images/image-8.png)  
![alt text](images/image-9.png)  

**Ejemplo:**  
![alt text](images/image-10.png)  
![alt text](images/image-11.png)  
---

### 4. Obtener Todas las Recetas

**Endpoint:** `GET /api/recipes`
![alt text](images/image-12.png)  
---

### 5. Obtener Receta por Número Consecutivo

**Endpoint:** `GET /api/recipes/{consecutiveNumber}`
![alt text](images/image-13.png)  
![alt text](images/image-14.png)  
---

### 6. Obtener Recetas de Participantes

**Endpoint:** `GET /api/recipes/contestant`
![alt text](images/image-17.png)  
---

### 7. Obtener Recetas de Televidentes

**Endpoint:** `GET /api/recipes/viewer`
![alt text](images/image-16.png)  
---

### 8. Obtener Recetas de Chefs

**Endpoint:** `GET /api/recipes/chef`
![alt text](images/image-18.png)  
---

### 9. Obtener Recetas por Temporada

**Endpoint:** `GET /api/recipes/season/{season}`
![alt text](images/image-19.png)  
---

### 10. Buscar Recetas por Ingrediente

**Endpoint:** `GET /api/recipes/search?ingredient={ingrediente}`
![alt text](images/image-20.png)
---

### 11. Actualizar Receta

**Endpoint:** `PUT /api/recipes/{consecutiveNumber}`
![alt text](images/image-21.png)  
![alt text](images/image-22.png)  
![alt text](images/image-23.png)  
---

### 12. Eliminar Receta

**Endpoint:** `DELETE /api/recipes/{consecutiveNumber}`
![alt text](images/image-24.png)  
![alt text](images/image-25.png)
---

## Documentación Swagger

### Swagger UI en Azure

La API cuenta con documentación Swagger desplegada en Azure:

**Swagger UI (Azure):**
```
https://recipe-api-doswcompany-encfd2f4ekbyhrhv.canadacentral-01.azurewebsites.net/swagger-ui/index.html
```

**Swagger UI (Local):**
```
http://localhost:8080/swagger-ui.html
```

### Características de Swagger

- **Documentación completa** de todos los endpoints
- **Ejemplos de request y response**
- **Pruebas en tiempo real** desde el navegador
- **Esquemas de datos** (DTOs y modelos)
- **Códigos de estado HTTP** documentados
- **Validaciones** 

---

## Testing

### Test requeridos:

- Validar que se pueda registrar una receta

```
    @Test
    @DisplayName("Should register viewer recipe successfully")
    void shouldRegisterViewerRecipeSuccessfully() throws Exception {
        // Given
        when(recipeService.registerViewerRecipe(any(ViewerRecipeRequestDto.class))).thenReturn(mockRecipe);

        // When & Then
        mockMvc.perform(post("/api/recipes/viewer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(viewerRecipeRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("12345")))
                .andExpect(jsonPath("$.title", is("Test Recipe")))
                .andExpect(jsonPath("$.chefName", is("Test Chef")))
                .andExpect(jsonPath("$.recipeType", is("VIEWER")))
                .andExpect(jsonPath("$.consecutiveNumber", is(1)));

        verify(recipeService).registerViewerRecipe(any(ViewerRecipeRequestDto.class));
    }

    @Test
    @DisplayName("Should register contestant recipe successfully")
    void shouldRegisterContestantRecipeSuccessfully() throws Exception {
        // Given
        mockRecipe.setRecipeType("CONTESTANT");
        mockRecipe.setSeason(3);
        when(recipeService.registerContestantRecipe(any(ContestantRecipeRequestDto.class))).thenReturn(mockRecipe);

        // When & Then
        mockMvc.perform(post("/api/recipes/contestant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contestantRecipeRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("12345")))
                .andExpect(jsonPath("$.recipeType", is("CONTESTANT")))
                .andExpect(jsonPath("$.season", is(3)));

        verify(recipeService).registerContestantRecipe(any(ContestantRecipeRequestDto.class));
    }

    @Test
    @DisplayName("Should register chef recipe successfully")
    void shouldRegisterChefRecipeSuccessfully() throws Exception {
        // Given
        mockRecipe.setRecipeType("CHEF");
        when(recipeService.registerChefRecipe(any(ChefRecipeRequestDto.class))).thenReturn(mockRecipe);

        // When & Then
        mockMvc.perform(post("/api/recipes/chef")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(chefRecipeRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("12345")))
                .andExpect(jsonPath("$.recipeType", is("CHEF")));

        verify(recipeService).registerChefRecipe(any(ChefRecipeRequestDto.class));
    }

    @Test
    @DisplayName("Should return bad request when viewer recipe has invalid data")
    void shouldReturnBadRequestWhenViewerRecipeHasInvalidData() throws Exception {
        // Given
        viewerRecipeRequestDto.setTitle(""); // Invalid title
        viewerRecipeRequestDto.setIngredients(Collections.emptyList()); // Invalid ingredients

        // When & Then
        mockMvc.perform(post("/api/recipes/viewer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(viewerRecipeRequestDto)))
                .andExpect(status().isBadRequest());

        verify(recipeService, never()).registerViewerRecipe(any());
    }
```


- Validar que la búsqueda por ingrediente devuelva resultados correctos
```
    @Test
    @DisplayName("Should search recipes by ingredient successfully")
    void shouldSearchRecipesByIngredientSuccessfully() throws Exception {
        // Given
        String ingredient = "tomate";
        List<Recipe> foundRecipes = Arrays.asList(mockRecipe, new Recipe());
        when(recipeService.searchRecipesByIngredient(ingredient)).thenReturn(foundRecipes);

        // When & Then
        mockMvc.perform(get("/api/recipes/search")
                .param("ingredient", ingredient))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is("12345")));

        verify(recipeService).searchRecipesByIngredient(ingredient);
    }

    @Test
    @DisplayName("Should return 404 when no recipes found by ingredient")
    void shouldReturn404WhenNoRecipesFoundByIngredient() throws Exception {
        // Given
        String ingredient = "ingrediente-inexistente";
        when(recipeService.searchRecipesByIngredient(ingredient))
            .thenThrow(new MasterChefException("No se encontraron recetas con el ingrediente: " + ingredient));

        // When & Then
        mockMvc.perform(get("/api/recipes/search")
                .param("ingredient", ingredient))
                .andExpect(status().isNotFound());

        verify(recipeService).searchRecipesByIngredient(ingredient);
    }
```

- Validar que se devuelva error si se consulta una receta inexistente
```
    @DisplayName("Should return 404 when recipe not found by consecutive number")
    void shouldReturn404WhenRecipeNotFoundByConsecutiveNumber() throws Exception {
        // Given
        Long consecutiveNumber = 999L;
        when(recipeService.getRecipeByConsecutiveNumber(consecutiveNumber))
            .thenThrow(new MasterChefException("No se encontró la receta con número consecutivo: " + consecutiveNumber));

        // When & Then
        mockMvc.perform(get("/api/recipes/{consecutiveNumber}", consecutiveNumber))
                .andExpect(status().isNotFound());

        verify(recipeService).getRecipeByConsecutiveNumber(consecutiveNumber);
    }
```


### Ejecutar Tests

```bash
# Ejecutar todos los tests
mvn test

# Ejecutar tests con cobertura (JaCoCo)
mvn clean test jacoco:report
```

### Ver Reporte de Cobertura

Después de ejecutar los tests con JaCoCo:

```bash
# El reporte HTML estará en:
open target/site/jacoco/index.html
```

## CI/CD

### GitHub Actions Workflows

El proyecto incluye dos workflows de CI/CD:  

#### 1. Workflow de Desarrollo (`ci.yml`)

**Trigger:** Push o Pull Request a la rama `develop`

**Funciones:**
- Ejecuta tests automáticamente
- Valida que el código compile

```yaml
# Ubicación: .github/workflows/ci.yml
# Se ejecuta en: push/PR a develop
```

#### 2. Workflow de Despliegue (`deploy-azure.yml`)

**Trigger:** Push a la rama `main`

**Funciones:**
- Compila la aplicación
- Empaqueta el JAR
- Despliega automáticamente en Azure

```yaml
# Ubicación: .github/workflows/deploy-azure.yml
# Se ejecuta en: push a main
```

