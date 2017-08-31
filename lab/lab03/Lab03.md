* Event จะถูกเข้า queue ไว้ เมื่อเริ่ม Run Application (ผู้เขียนจะต้องจัด event ให้ดี)
* ถ้า layout ซ้อนกันเยอะ จะทำให้ process app ช้าเพราะใช้ resource เยอะมากเกินไป (layout ซ้อนกันห้ามเกิน 3 ชั้น)
* View เอาข้อมูลจาก model มาแสดงผล แต่ไม่สามารถตัดสินใจได้ว่า จะแสดงผลเมื่อไหร่? เปรียบเสมือนเครื่องคอมพิวเตอร์ มันจะเปิดเองไม่ได้ แต่ถ้าเรา กดปุ่ม (action จากผู้ใช้งาน หรือ event) 
* Controller จะเป็นผู้ดักจับ event ต่างๆ จากผู้ใช้งาน เมื่อมี event เกิดขึ้นจะส่งไปบอก view (ปัญหาในการทำคือ เรามักจะเอา mvc มาอยู่หน้าเดียวกัน)

   (keypresses, taps, etc.)           (update)      (invalidate)    (redraw)

App ---------------------> Controller -------> Model ---------> View ------> App

* โครงสร้างของ View จะนำเสนอในรูปแบบของ tree ยิ่ง tree มันลึก จะแสดงผลช้า (เหตุผลที่ห้าม layout ซ้อนกันเยอะเกิน 3 ชั้น)
* SDK Manager จะมี Android SDK Location a.f.a Android Home
* device simulator refresh display 60 time/second
* android มี 2 thread 
  1. ui thread
  2. background thread
* background thread จะใช้กับ process ที่ทำงานนาน เช่น การดึงข้อมูลจาก api server เป็นการ asynchronous thread
* ใน android oreo ถ้า bg thread run นานเกินไป มันจะ remove ทิ้งทันที
* View Group เช่น list view ect.

* text = "@string/" (ห้าม hardcode เพราะว่า android มีหลากหลายภาษา ให้เขียนใน /value/string.xml เป็นแนวปฏิบัติที่ดีในการเขียน)
* การดัก event ถ้ามี btn หลายๆ ปุ่ม จะต้องใส่ listener
* class R จะถูก generate ขึ้นมาเพื่อเก็บ ชื่อ id ของ ui
* การ notify ใน java เรียกว่า callback 
* link library android เช่น awesome android, android arsenal
* การจะวาดรูปบน view ต้องใช้ type Paint
* .invalidate() เป็นฟังก์ชันที่ไว้วาดใหม่