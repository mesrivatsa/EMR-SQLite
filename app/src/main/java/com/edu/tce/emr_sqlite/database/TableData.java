package com.edu.tce.emr_sqlite.database;

import android.provider.BaseColumns;

/**
 * Created by RaamKumr on 3/20/2017.
 */

class TableData {

    public TableData() {
    }

    static abstract class TableInfo implements BaseColumns
    {
        static final String DOCTOR_PROFILE = "doctor_profile";
        static final String DOCTOR_NAME = "doctor_name";
        static final String DOCTOR_PASSWORD = "doctor_password";
        static final String DOCTOR_AGE = "doctor_age";
        static final String DOCTOR_ADDRESS = "doctor_address";
        static final String DOCTOR_EMAIL = "doctor_email";
        static final String DOCTOR_PHONE = "doctor_phone";
        static final String DOCTOR_SPECIALIZATION = "doctor_specialization";
        static final String DATABASE_NAME = "doctor_info";
        static final String TABLE_NAME = "doctor_reg_info";
    }

    static abstract class patientTableInfo implements BaseColumns
    {
        static final String PATIENT_PROFILRE = "patient_profile";
        static final String PATIENT_NAME = "patient_name";
        static final String PATIENT_PASSWORD = "patient_password";
        static final String PATIENT_AGE = "patient_age";
        static final String PATIENT_ADDRESS = "patient_address";
        static final String PATIENT_EMAIL = "patient_email";
        static final String PATIENT_PHONE = "patient_phnone";
        static final String DATABASE_NAME = "patient_info";
        static final String PATIENT_TABLE_NAME = "patient_reg_info";
    }
}
