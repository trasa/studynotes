
namespace ConnectFour.Game
{
    /// <summary>
    /// Examine the state of a Container after a piece has been added.
    /// Is this Container now in a "win" state?
    /// </summary>
    public class WinDetector
    {
        private readonly Container container;
        private readonly PieceColor color;
        private readonly int column;
        private readonly int row;

        public WinDetector(Container container, PieceColor color, int column, int row)
        {
            this.container = container;
            this.color = color;
            this.column = column;
            this.row = row;
        }

        public bool IsWinner
        {
            get { return WinsHorizontally || WinsVertically || WinsPositiveDiagonally || WinsNegativeDiagonally; }
        }

        protected bool WinsNegativeDiagonally
        {
            get
            {
                return container.GetPieceCountNegativeUp(color, column, row) +
                       container.GetPieceCountNegativeDown(color, column, row) + 1 >= 4;
            }
        }

        protected bool WinsPositiveDiagonally
        {
            get
            {
                return container.GetPieceCountPositiveUp(color, column, row) +
                       container.GetPieceCountPositiveDown(color, column, row) + 1 >= 4;
            }
        }

        protected bool WinsVertically
        {
            get
            {
                return container.GetPieceCountUp(color, column, row) +
                       container.GetPieceCountDown(color, column, row) + 1 >= 4;
            }
        }

        protected bool WinsHorizontally
        {
            get
            {
                return container.GetPieceCountLeft(color, column, row) +
                       container.GetPieceCountRight(color, column, row) + 1 >= 4;
            }
        }
    }
}
