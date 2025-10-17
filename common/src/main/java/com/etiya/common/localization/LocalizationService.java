package com.etiya.common.localization;

public interface LocalizationService {
    String getMessage(String key);
    //Buraya key verilır. Dönüş olarak ilgili sınıflardan hangi dili verdiysek o mesaj döner
}
