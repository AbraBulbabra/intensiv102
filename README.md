Создать пустой репозиторий на GitHub, склонировать его себе.

Создать новый проект, в качестве системы сборки использовать maven или gradle (по вашему выбору).

Отвести от основной ветки ветку home-task2.

Разобраться, как создать свое исключение (созданное вами, а не из числа существующих). Создать класс, написать в нем метод, логика которого создает ситуацию, приводящую к выбрасыванию вашего исключения, в самом методе не нужно перехватывать это исключение (использовать throws в сигнатуре метода).
Написать еще один (не имеющий throws в сигнатуре) метод, в котором будет происходить вызов первого метода (кидающего исключение), перехватить его и корректно обработать. При перехвате исключения вывести лог (можно через System.out), что было перехвачено исключение, лог должен содержать информацию о перехваченном исключении.   

Проделанную работу запушить в вашу ветку в удаленном репозитории и создать pull request в основною ветку (но не вливать его до ревью преподавателем!). Если термин pull request вам незнаком, необходимо изучить, что это. Не забыть про .gitignore, в репозитории не должно быть директорий .idea, target со скомпилированным байткодом и т.п.
Ссылку на pull request отправить вместе с файлом, содержащим ответ на п.1 задания