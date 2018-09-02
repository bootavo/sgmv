package com.app.sgmv.sgmv.utilities;

import com.app.sgmv.sgmv.entities.failure.FailureReport;
import com.app.sgmv.sgmv.entities.failure.MainComponent;
import com.app.sgmv.sgmv.entities.failure.WheelComponent;

import java.util.List;

public class Constants {

    //AWS S3
    public static String AWS_KEY = "AKIAIKQ3OWI2YHTNHRKQ";
    public static String AWS_SECRET = "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";

    //VALIDATE MESSAGE
    public static String MSJ_ERROR_EMPTY_FIELDS = "Complete los campos solicitados";

    //ERROES CONEXION SERVICIOS
    public static String MJS_ERROR_CONEXION_SERVICIO = "Estamos teniendo problemas con la conexión";

    //SUCCESS MESSAGES
    public static String MJS_CONFIRMACION_MARCAR_ENTRADA = "Se marcó su entrada correctamente";
    public static String MJS_CONFIRMACION_MARCAR_SALIDA = "Se marcó su salida correctamente";
    public static String MJS_ERROR_MARCAR_ENTRADA = "No se marcó su entrada";
    public static String MJS_ERROR_MARCAR_SALIDA = "No se marcó su salida";
    public static String MJS_NO_LIST_LUGTRABAJO = "Arreglo de LugTrabajo vacio";
    public static String MJS_NO_HIST_STORAGED = "No tiene asistencias registradas en el mes";

    //FAILED MESSAGES
    public static String MJS_ERROR_INVALID_INPUT = "Valores inválido";
    public static String MJS_ERROR_INVALID_INPUT_USER = "User inválido";
    public static String MJS_ERROR_INVALID_INPUT_PASSWORD = "Clave inválida";
    public static String MJS_ERROR_HIST_ASSITS = "No se pudo cargar su historial del mes";
    public static String MJS_ERROR_CREDENTIALS = "Las credenciales ingresadas son invalidas";
    public static String MJS_ERROR_VALOR_NULO = "El objeto tiene valores no validos";
    public static String MJS_ERROR_COD_EMP = "Valor de codigo de empleado no valido";
    public static String MJS_ERROR_WIFI = "El wifi no esta disponible";
    public static String MJS_ERROR = "Ocurrio un error en el proceso";

    //ERROR
    public static String ERROR = "ERROR";

    //MENU OPTIONS
    public static String TAG_MODULE = "module";
    public static final String TAG_LIST = "list";

    public static final String TAG_TYPE_EMPLOYEE = "employee";
    public static final String TYPE_EMPLOYEE = "employee";
    public static final String TYPE_DRIVER = "driver";
    public static String EMPLOYEE_NAME = "";
    public static int EMPLOYEE_ID = 0;

    public static String TAG_TYPE_VEHICLE = "vehicle";
    public static String TYPE_TRACT = "tract";
    public static String TYPE_SEMITRAILER = "semitrailer";
    public static String TRACT_PLACA = "";
    public static String SEMITRAILER_PLACA = "";
    public static int TRACT_ID = 0;
    public static int SEMITRAILER_ID = 0;

    public static final String BOSS_OPERATIONS = "Jefe de Operaciones";
    public static final String DRIVER = "Chofer";
    public static final String MANAGEMENT = "Administrador";

    public static final String M_MAGNAMENT = "Gestión de Operaciones";
    public static final String M_REGISTER = "Registro";
    public static final String M_REPORT = "Reportes";
    public static final String M_SEARCH = "Búsqueda";

    public static final String SM_FAILURE = "Averías";
    public static final String SM_USERS = "Usuarios";
    public static final String SM_SAFE = "Seguros";
    public static final String SM_MAINTENANCE = "Mantenimiento";
    public static final String SM_VEHICLE = "Vehículos";
    public static final String SM_COSTOS = "Costos";

    public static FailureReport FAILURE_REPORT_OBJECT = null;
    public static List<MainComponent> LIST_MAIN_COMPONENTE = null;
    public static List<WheelComponent> LIST_WHEEL_COMPONENT = null;

}
