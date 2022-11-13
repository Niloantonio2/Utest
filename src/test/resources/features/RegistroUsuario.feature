#language: es
#Author: Nilo Chaverra  normalista7@gmail.com
  @Historias
  Característica: Registro de usuario en Utest
    El usuario puede ingresar a la pagina web de Utest y completar el formulario de registro mediante la opción Únete hoy

    @escenario1
    Escenario: Registro exitoso
      Dado que el usuario quiere registrarse en la pagina de Utest
      Cuando el usuario completa los campos requeridos
        | strNombre | strAprllido | strCorreo      | strDiaNacimiento | strMesNacimiento | strAnoNacimiento | strCiudad | strCodigoPostal | strPais  | strComputador | strVersionComputador | strLenguajeComputador | strDispositivoMovil | strModeloDispositivoMovil | strSistemaOperativoMovil | strClave    |
        | Nilo      | Chaverra    | Nilo@utest.com | 15               | October          | 1994             | Medellin  | 123             | Colombia | Windows       | 7                    | Spanish               | Apple               | iPhone                    | iOS 11.2                 | *R3t0_2022$ |
      Entonces el registro se realizo con exito
      | strMensajeFinal |
      | Welcome to the world's largest community of freelance software testers! |

