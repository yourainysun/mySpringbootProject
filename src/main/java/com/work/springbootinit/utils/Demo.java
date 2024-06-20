package com.work.springbootinit.utils;

/*
package com.work.springbootinit.utils;

class Person{
    public Person(){
        System.out.println( "this is a Person"  );
    }
}
public class Teacher extends Person{
    private String name= "tom";
    public Teacher(){
        System.out .println( " this is a teacher"  );
        super();
    }
    public static void main(String[] args){
        Teacher teacher = new Teacher();
        System.out.println(this.name);
        Thread
    }
}
*/
class HasStatic{
    private static int x=100;
    public static void main(String args[ ]){
        HasStatic hs1=new HasStatic( );
              hs1.x++;
              HasStatic hs2=new HasStatic( );
            hs2.x++;
             hs1=new HasStatic( );
              hs1.x++;
              HasStatic.x--;
             System.out.println( "x="+x);
         }
 }