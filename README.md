# People Registry System POC

This is a proof of concept implementation for a REST service that replaces a legacy backend for a people registry system. The service allows users to save and retrieve information about a person using their social security number as the identifier. It also provides functionality to store the name of a person's spouse (if applicable) and the names and ages of their children.

## Assumptions

- This POC assumes a simple implementation without persistence or a complete REST application framework.
- The focus is on implementing the domain model and the core functionality of saving and retrieving persons.
- The implementation is done using Java SDK without any external dependencies, except for a junit test framework.
- Data loss on restarts is acceptable, meaning the data is not persisted across application restarts.

## Domain Model

The domain model consists of two main entities: `Person` and `Child`.

### Person

The `Person` class represents an individual and contains the following attributes:

- `ssn` (Social Security Number): A unique identifier for each person.
- `name`: The name of the person.
- `spouseName`: The name of the person's spouse (optional).

### Child

The `Child` class represents a child of a person and contains the following attributes:

- `name`: The name of the child.
- `age`: The age of the child.

## Functionality

The core functionality of the POC includes the following operations:

1. Save a person with their information, including their social security number, name, spouse's name and children (if applicable).
2. Retrieve information about a person by their social security number.
3. Retrieve the oldest child for a given person, along with their social security number.

## Instructions for Future Development

List of APIs:
- Save a Person
- Retrieve a Person
- Retrieve the Oldest Child for a Given Person


#### 1. Save a Person

```http
  POST v1/api/person
```

| Parameter             | Type     | Description                |
|:----------------------| :------- | :------------------------- |
| `ssn`                 | `string` | **Required**. The social security number of the person              |
| `name`                | `string` | **Required**. Name of the person              |
| `spouseName`        | `string` | Optional. The name of the person's spouse. Leave it empty if not applicable.              |
| `children`            | `string` | Optional. An array containing information about the person's children.              |
| `children > name`     | `string` | **Required**. Name of the child              |
| `children > birthday` | `string` | **Required**. Birthday of the child            |

Request Body:

```json
{
    "ssn": "901214-5632",
    "name": "John",
    "spouseName": "Karin",
    "children": [{
        "name":"Kai",
        "birthday": "2000-02-15"
    },
    {
        "name":"Eliana",
        "birthday": "2005-09-28"
    }]
}
```

Response
- Status Code: 201 (Created)
- Response Body: None

#### 2. Retrieve a Person

```http
  GET v1/api/person/{ssn}
```

Response
- Status Code: 200 (Success)
- Response Body:
```json
{
  "ssn": "901214-5632",
  "name": "John",
  "spouseName": "Karin",
  "children": [{
    "name":"Kai",
    "birthday": "2000-02-15"
  },
    {
      "name":"Eliana",
      "birthday": "2005-09-28"
    }]
}
```

#### 3. Retrieve the Oldest Child for a Given Person

```http
  GET v1/api/person/{ssn}/oldestchild
```
Response
- Status Code: 200 (Success)
- Response Body:
```json
{
  "name":"Kai",
  "birthday": "2000-02-15"
}
```
## Running Tests

To run tests, run the following command

```bash
  mvn test
```