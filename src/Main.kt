fun main() {


    fun isValidIPV4(ip: String): Boolean {
        // تأكد أن السلسلة لا تبدأ أو تنتهي بنقطة ولا تحتوي على نقطتين متتاليتين
        if (ip.startsWith(".") || ip.endsWith(".") || ".." in ip) return false

        val parts = ip.split(".")

        // يجب أن يكون هناك 4 أجزاء بالضبط
        if (parts.size != 4) return false

        for (part in parts) {
            if (part.isEmpty()) return false
            if (part.any { !it.isDigit() }) return false

            try {
                val num = part.toInt()
                if (num !in 0..255) return false
                if (part.length > 1 && part.startsWith("0")) return false // لا تبدأ بـ 0
            } catch (e: NumberFormatException) {
                return false
            }
        }

        return true
    }
    println(isValidIPV4("192.168.1.1"))

    check(name = "when 255.255.255.255", result = isValidIPV4("255.255.255.255"), correctResult = true)
    check(name = "when 192.168.1.1", result = isValidIPV4("192.168.1.1"), correctResult = true)
    check(name = "when 0.0.0.0", result = isValidIPV4("0.0.0.0"), correctResult = true)
    check(name = "when 8.8.8.8", result = isValidIPV4("8.8.8.8"), correctResult = true)
    check(
        name = "When IPv4 is out of range 256.256.256.256",
        result = isValidIPV4("256.256.256.256"),
        correctResult = false,
    )
    check(
        name = "When IPv4 is less than 4 segments 192.168.1 ",
        result = isValidIPV4("192.168.1"),
        correctResult = false
    )
    check(
        name = "When IPv4 has more than 4 segments  192.168.1.1.1",
        result = isValidIPV4("192.168.1.1.1"),
        correctResult = false
    )
    check(name = "When IPv4 starts with zero 192.168.01.1",
        result = isValidIPV4("192.168.01.1"),
        correctResult = false)
    check(
        name = "When IPv4 contains a character 192.168.1.a ",
        result = isValidIPV4("192.168.1.a"),
        correctResult = false
    )
    check(
        name = "When IPv4 contains a negative number ",
        result = isValidIPV4("-1.168.1.1"),
        correctResult = false)
    check(
        name = "When IPv4 is empty ",
        result = isValidIPV4(""),
        correctResult = false)
    check(
        name = "when Ipv4 contains two consecutive dots 192..168.1.1",
        result = isValidIPV4("192..168.1.1"),
        correctResult = false
    )
    check(
        name = "When there is a space between IPv4 segments 192. 168.1.1",
        result = isValidIPV4("192. 168.1.1"),
        correctResult = false
    )
    check(
        name = "When there is a space at the beginning of IPv4  192.168.1.1",
        result = isValidIPV4(" 192.168.1.1"),
        correctResult = false
    )
    check(
        name = "When there is a space at the end of IPv4 192.168.1.1 ",
        result = isValidIPV4("192.168.1.1 "),
        correctResult = false
    )
}

fun check(name: String, result: Boolean, correctResult: Boolean) {
    if (result == correctResult) {
        println("Success -> $name")
    } else {
        println("Falid -> $name")
    }
}