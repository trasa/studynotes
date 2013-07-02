using System;
using System.Runtime.Serialization;

namespace ConnectFour.GameApi
{
    /// <summary>
    /// An attempt was made to put a piece somewhere it didn't belong.
    /// </summary>
    public class IllegalPlacementException : Exception
    {
        public IllegalPlacementException()
        {
        }

        public IllegalPlacementException(string message) : base(message)
        {
        }

        public IllegalPlacementException(string message, Exception innerException) : base(message, innerException)
        {
        }

        protected IllegalPlacementException(SerializationInfo info, StreamingContext context) : base(info, context)
        {
        }
    }
}
