export const Task = ({ ...props }) => (
  <div data-testid={`task-${props.id}`} className="task">
    <p>My task</p>
  </div>
);
