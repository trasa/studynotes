using System;
using System.Linq;
using System.Reflection;
using NUnit.Framework;

namespace ConnectFour.Test
{
    [TestFixture]
    public class Generify
    {
        public object DoStuff(string blah)
        {
            Console.WriteLine("Do stuff not generic");
            return blah;
        }

        public T DoStuff<T>(string blah) where T : new() 
        {
            Console.WriteLine("Do stuff on type on string");
            return new T();
        }

        public T DoStuff<T>(object blargh) where T : new()
        {
            Console.WriteLine("do stuff on type on object");
            return new T();
        }

        [Test]
        public void DoGeneric()
        {
            // doesn't work, this is ambiguous:
//            MethodInfo method = typeof(Generify).GetMethod("DoStuff", new[] {typeof(string)});

            // also doesn't work, DoStuff<T>(..) is ambiguous
//            MethodInfo method = typeof(Generify)
//                .GetMethods().Single(m => m.IsGenericMethod && m.Name == "DoStuff");

            MethodInfo method = typeof(Generify)
            .GetMethods().Single(m => m.IsGenericMethod && 
                m.Name == "DoStuff" && 
                m.GetParameters()[0].ParameterType == typeof(string));

            method = method.MakeGenericMethod(new[] {typeof(Generify)});
            method.Invoke(this, new object[] {"blah"});
        }
    }
}
