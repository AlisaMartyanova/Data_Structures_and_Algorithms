public class DoublyLinkedList<E> {

    Node<String> node1, node2, node3, node4;


    public static void main(String[] args)
    {

        Node<String> node1 = new Node<String>("Hello");
        Node<String> node2 = new Node<String>("Java");
        Node<String> node3 = new Node<String>("World");
        Node<String> node4 = new Node<String>("Earth");

        DoublyLinkedList list = new DoublyLinkedList();

        System.out.println(list.IsEmpty());

        list.AddFirst(node1);
        //list.AddLast(node3);
        list.AddLast(node2);
        list.Add(1, node3);

        list.Print();
        System.out.println(list.IsEmpty());

        list.Set(2, node4);

        //System.out.println(list.Get(2));

        /*list.DeletAt(2);*/
        list.Print();

        /*list.Delet("World");
        list.Print();
        list.DeletFirst();
        System.out.println(list.IsEmpty());*/

    }


    public void Print()   // Print information about the list
    {
        Node node = head;

        if (count == 0) {System.out.println("This list is empty");}
        else
        {
            while (node != null) {
                System.out.print(node.value + " ");

                if (node.next != null)
                {
                    node = node.next;
                }
                else
                {
                    break;
                }
            }
            System.out.println();
            System.out.println("Number of nodes: " + count);
        }
    }


    Node<E> head, tail;
    int count;

    public DoublyLinkedList()
    {
        head = null;
        tail = null;
    }

    ////////////

    public void AddLast (Node<E> node)   // Insert the given node at the end of the list
    {
        if (head == null)
        {
            head = node;
        }
        else
        {
            tail.next = node;
            node.prev = tail;
        }
        tail = node;
        count ++;
    }

    ////////////

    public Node Find(E val)    // Find and return the first node with given value
    {
        Node node = head;
        while (node != null)
        {
            if (node.value == val)
            {
                return node;
            }
            else
            {
                node.next = node;
            }
        }
        return null;
    }

    ///////////////

    public void Set(int i, Node<E> node)
    {
        Node<E> TempNode = head;
        if ((i>=0) && (i<count))
        {
            if (i == 0)
            {
                DeletFirst();
                AddFirst(node);
            }
            else if (i == count -1)
            {
                DeletLast();
                AddLast(node);
            }
            else {

                int j = 0;
                while (j!=i)
                {
                    TempNode = TempNode.next;
                    j++;
                }
                TempNode.prev.next = node;
                node.prev = TempNode.prev;
                node.next = TempNode.next;
                TempNode.next.prev = node;
            }

        }
    }

    ////////////////

    public void AddFirst (Node<E> node)   // Insert the given node at the beginning of the list
    {
        if (head == null)
        {
            head = node;
            tail = node;
        }
        else
        {
            node.next = head;
            head = node;
        }

        count ++;
    }

    //////////////

    public void Add(int i, E val)
    {
        Node<E> node = new Node<E> (val);
        Node<E> TempNode = head;
        if ((i>=0) && (i<= count))
        {
            if (i == 0)
            {
                AddFirst(val);
            }
            else if (i == count)
            {
                AddLast(val);
            }

            else
            {
                int j = 0;
                while (j!=i)
                {
                    TempNode = TempNode.next;
                    j++;
                }

                TempNode.prev.next = node;
                node.prev = TempNode.prev;
                node.next = TempNode;
                TempNode.prev = node;
                count++;
            }


        }
    }

    //////////////

    public boolean IsEmpty()   // Checks if the list is empty
    {
        if (count == 0)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    //////////

    public void DeletFirst()  // Remove the firts element of the list
    {
        if (count == 1)
        {
            head = null;
            tail = null;
            count --;
        }
        else if (count > 1)
        {
            head = head.next;
            head.prev = null;
            count --;
        }
    }

    ////////////////

    public void DeletLast()    // Remove the last element of the list
    {
        if (count == 1)
        {
            head = null;
            tail = null;
            count --;
        }
        else if (count > 1)
        {
            tail = tail.prev;
            tail.next = null;
            count --;
        }
    }

    /////////////

    public void DeletAt(int i)
    {
        Node TempNode = head;
        if ((i>=0) && (i<=count))
        {
            if (i == 0)
            {
                DeletFirst();
            }
            else if (i == count -1)
            {
                DeletLast();
            }
            else {

                int j = 0;
                while (j!=i)
                {
                    TempNode = TempNode.next;
                    j++;
                }
                TempNode.prev.next = TempNode.next;
                TempNode.next.prev = TempNode.prev;
                count--;
            }

        }

    }


    public E Get(int i)
    {
        Node<E> TempNode = head;
        if ((i>=0) && (i<=count))
        {
            if (i == 0)
            {
                return head.value;
            }
            else if (i == count -1)
            {
                return tail.value;
            }
            else {

                int j = 0;
                while (j!=i)
                {
                    TempNode = TempNode.next;
                    j++;
                }
                return TempNode.value;
            }

        }
        return head.value;
    }

    ///////////////

    public void Delet(E val)  // Remove an element by its value
    {
        Node node = head;
        /*if (count == 0)
        {
            System.out.println("Error");
        }*/

        if (count == 1)
        {
            head = null;
            count = 0;
        }

        else if (val == head.value && count>1)
        {
            DeletFirst();
        }

        else if (val == tail.value && count>1)
        {
            DeletLast();
        }

        else if (count > 1)
        {
            while (node != null) {
                if (node.next != null && node.next.value == val)
                {
                    if (node.next.next != null)
                    {
                        node.next = node.next.next;
                        node.next.prev = node;
                        count --;
                        //   node = node.next;

                        break;
                    }

                    else
                    {
                        node.next = null;
                        tail = node;
                        count --;
                        break;
                    }
                }


                if (node.next != null)
                {
                    node = node.next;
                }
                else
                {
                    break;
                }

            }

        }
    }

}
